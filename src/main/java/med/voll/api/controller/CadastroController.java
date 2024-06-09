package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosCadastroUsuarioDTO;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.UsuarioListagemDTO;
import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("addUser")
public class CadastroController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<UsuarioListagemDTO> cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuarioDTO dados) {
        Usuario usuario = new Usuario(dados);

        usuarioRepository.save(usuario);
        return ResponseEntity.ok(new UsuarioListagemDTO(usuario));
    }
}
