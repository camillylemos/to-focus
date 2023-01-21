package br.com.ifsul.tcc.aplicacao.services.usuario;

import br.com.ifsul.tcc.aplicacao.security.UsuarioAutenticado;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioLogadoService {

    public UsuarioAutenticado get() {
        return (UsuarioAutenticado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
