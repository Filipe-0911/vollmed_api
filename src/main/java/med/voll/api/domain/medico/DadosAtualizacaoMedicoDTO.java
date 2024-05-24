package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.DadosEnderecoDTO;

public record DadosAtualizacaoMedicoDTO(
        String nome,
        String telefone,
        DadosEnderecoDTO endereco,
        Especialidade especialidade) {

}
