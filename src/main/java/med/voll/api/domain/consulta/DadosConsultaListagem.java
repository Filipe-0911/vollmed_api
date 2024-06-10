package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.DadosListagemMedicoDTO;
import med.voll.api.domain.paciente.DadosListagemPacienteDTO;

import java.time.LocalDateTime;

public record DadosConsultaListagem(
        LocalDateTime data,
        DadosListagemMedicoDTO medico,
        DadosListagemPacienteDTO paciente,
        MotivoCancelamento motivoCancelamento,
        boolean cancelada
) {
    public DadosConsultaListagem(Consulta consulta) {
        this(consulta.getData(),
                new DadosListagemMedicoDTO(consulta.getMedico()),
                new DadosListagemPacienteDTO(consulta.getPaciente()),
                consulta.getMotivoCancelamento(),
                consulta.getCancelada());
    }

}
