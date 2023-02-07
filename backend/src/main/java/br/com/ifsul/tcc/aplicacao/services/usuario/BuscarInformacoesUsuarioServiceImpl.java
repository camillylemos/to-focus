package br.com.ifsul.tcc.aplicacao.services.usuario;

import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarInformacoesUsuarioServiceImpl implements BuscarInformacoesUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Usuario buscarInformacoesUsuario(String token) {
        Usuario usuario = usuarioRepository.findByToken(token);

        //TODO add validate

        return usuario;
    }
}
