package br.com.ifsul.tcc.aplicacao.services.usuario;

import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.security.UsuarioAutenticado;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAutenticadoService {

    public Usuario get() {
        (UsuarioAutenticado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Usuario usuario =
    }
}
