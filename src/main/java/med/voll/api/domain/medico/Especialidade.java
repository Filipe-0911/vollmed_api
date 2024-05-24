package med.voll.api.domain.medico;

public enum Especialidade {
    ORTOPEDIA("ortopedia"),
    CARDIOLOGIA("cardiologia"),
    GINECOLOGIA("ginecologia"),
    PSIQUIATRIA("psiquiatria"),
    PEDIATRIA("pediatria"),
    GASTROENTEROLOGIA("gastroenterologia"),
    ENDOCRINOLOGIA("endocrinologia"),
    DERMATOLOGIA("dermatologia");

    private String especialidadeDefinida;

    Especialidade(String especialidadeRecebida) {
        this.especialidadeDefinida = especialidadeRecebida;
    }

    private Especialidade fromString(String especialidadeRecebida) {
        for (Especialidade especialidade : Especialidade.values()) {
            if (especialidade.especialidadeDefinida.equalsIgnoreCase(especialidadeRecebida)) {
                return especialidade;
            }
        }
        throw new IllegalArgumentException("Nenhuma especialidade encontrada para a string fornecida: " + especialidadeRecebida);
    }
}
