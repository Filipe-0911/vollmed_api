package med.voll.api.medico;

import med.voll.api.endereco.DadosEnderecoDTO;

public record DadosAtualizacaoMedicoDTO(
        String nome,
        String telefone,
        DadosEnderecoDTO endereco,
        Especialidade especialidade) {

}
