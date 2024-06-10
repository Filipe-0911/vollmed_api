package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MotivoCancelamento {
    PACIENTE_DESISTIU("paciente_desistiu"),
    MEDICO_CANCELOU("medico_cancelou"),
    OUTROS("outros");

    private String motivoCancelamento;

    MotivoCancelamento(String motivo) {
        this.motivoCancelamento = motivo;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    @JsonCreator
    public static MotivoCancelamento fromString(String value) {
        for (MotivoCancelamento especialidade : MotivoCancelamento.values()) {
            if (especialidade.name().equalsIgnoreCase(value)) {
                return especialidade;
            }
        }
        throw new IllegalArgumentException("Especialidade inválida: " + value);
    }
}
