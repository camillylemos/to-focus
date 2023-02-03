package br.com.ifsul.tcc.aplicacao.services.usuario;

import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAutenticadoServiceImpl implements UsuarioAutenticadoService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario get(String token) {
//        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Usuario usuario = usuarioRepository.findByToken(token);

        return usuario;
    }
}
