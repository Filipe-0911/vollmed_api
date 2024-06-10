package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar (DadosAgendamentoConsulta dados) {

        if (dados.idPaciente() == null) {
            return;
        }
        boolean pacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());

        if(!pacienteAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído.");
        }
    }
}
