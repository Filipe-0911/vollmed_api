package med.voll.api.domain.medico;

public record DadosListagemMedicoDTO(Long id,
                                     String nome,
                                     String email,
                                     String crm,
                                     Especialidade especialidade) {
    public DadosListagemMedicoDTO(Medico m) {
        this(m.getId(), m.getNome(), m.getEmail(), m.getCrm(), m.getEspecialidade());
    }
}
