package br.com.ifsul.tcc.aplicacao.services.usuario;

import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.exceptions.RegistroJaPertenceABaseException;
import br.com.ifsul.tcc.aplicacao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class CadastrarUsuarioServiceImpl implements CadastrarUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void cadastrarUsuario(Usuario request) {

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail());

        if (usuario != null) {
            throw new RegistroJaPertenceABaseException("Esta email pertence a uma conta já existente!");
        }

        request.setSenha(Base64.getEncoder().encodeToString(request.getSenha().getBytes()));

        usuarioRepository.save(request);
    }

}
