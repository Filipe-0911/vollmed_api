package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoConsulta;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorAgendamentoConsulta> validadorAgendamentoConsultas;
    @Autowired
    private List<ValidadorCancelamentoConsulta> validadorCancelamentoConsultas;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if(!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("ID do paciente informado não existe!");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("ID do medico informado não existe!");
        }

        validadorAgendamentoConsultas.forEach(v -> v.validar(dados));

        Paciente paciente = pacienteRepository.findById(dados.idPaciente()).get();
        Medico medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico dessa especialidade disponível nesta data.");
        }

        Consulta consulta = new Consulta(null, medico, paciente, dados.data(), null, false);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido.");
        }
        return medicoRepository.escolherMedicoAleatorioNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        if (!consultaRepository.existsById(dadosCancelamentoConsulta.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }
        validadorCancelamentoConsultas.forEach(v -> v.valida(dadosCancelamentoConsulta));

        var consulta = consultaRepository.getReferenceById(dadosCancelamentoConsulta.idConsulta());
        consulta.cancelar(dadosCancelamentoConsulta.motivo());

    }
}
