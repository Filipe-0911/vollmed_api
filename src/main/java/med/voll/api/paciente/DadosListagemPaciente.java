package med.voll.api.paciente;

import med.voll.api.medico.Medico;

public record DadosListagemPaciente(Long id,String nome, String email, String cpf) {
    public DadosListagemPaciente(Paciente p) {
        this(p.getId(), p.getNome(), p.getEmail(), p.getCpf());
    }
}
