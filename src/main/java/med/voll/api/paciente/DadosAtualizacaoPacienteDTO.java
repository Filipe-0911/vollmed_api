package med.voll.api.paciente;

import med.voll.api.endereco.DadosEnderecoDTO;

public record DadosAtualizacaoPacienteDTO(
        String nome,
        String telefone,
        DadosEnderecoDTO endereco) {

}
