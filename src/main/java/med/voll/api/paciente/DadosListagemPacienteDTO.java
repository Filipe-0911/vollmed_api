package med.voll.api.paciente;

public record DadosListagemPacienteDTO(Long id, String nome, String email, String cpf) {
    public DadosListagemPacienteDTO(Paciente p) {
        this(p.getId(), p.getNome(), p.getEmail(), p.getCpf());
    }
}
