package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.DadosEnderecoDTO;

public record DadosAtualizacaoPacienteDTO(
        String nome,
        String telefone,
        DadosEnderecoDTO endereco) {

}
