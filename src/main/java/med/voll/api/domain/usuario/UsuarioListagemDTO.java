package med.voll.api.domain.usuario;

public record UsuarioListagemDTO(
        String login,
        String mensagem
) {
    public UsuarioListagemDTO(Usuario usuario) {
        this(usuario.getLogin(), "Conta %s criada com sucesso!".formatted(usuario.getLogin()));

    }
}
