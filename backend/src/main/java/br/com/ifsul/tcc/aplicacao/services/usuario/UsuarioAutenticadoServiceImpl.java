package br.com.ifsul.tcc.aplicacao.services.usuario;

import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.repository.UsuarioRepository;
import br.com.ifsul.tcc.aplicacao.security.UsuarioAutenticado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAutenticadoServiceImpl implements UsuarioAutenticadoService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario get() {
        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Usuario usuario = usuarioRepository.findByEmail(usuarioAutenticado.getEmail());

        return usuario;
    }
}