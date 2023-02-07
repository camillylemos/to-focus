package br.com.ifsul.tcc.aplicacao.services.usuario;

import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.repository.UsuarioRepository;
import br.com.ifsul.tcc.aplicacao.represetation.request.LogarUsuarioRequest;
import br.com.ifsul.tcc.aplicacao.represetation.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
public class LogarUsuarioServiceImpl implements LogarUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public TokenResponse logarUsuario(LogarUsuarioRequest request) {

        Usuario usuario = usuarioRepository.login(request.getEmail(), Base64.getEncoder().encodeToString(request.getSenha().getBytes()));

        //todo add validate
        if (usuario != null) {
            String token = UUID.randomUUID().toString();
            usuario.setToken(token);
            usuarioRepository.save(usuario);
            return new TokenResponse(token);
        }

        return null;
    }
}
