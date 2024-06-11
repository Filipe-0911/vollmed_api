package med.voll.api.domain.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encriptaSenhaUsuario(String senha) {
        return passwordEncoder.encode(senha);
    }
}
