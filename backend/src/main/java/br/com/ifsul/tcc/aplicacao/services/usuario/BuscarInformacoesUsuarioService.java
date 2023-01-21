package br.com.ifsul.tcc.aplicacao.services.usuario;

import br.com.ifsul.tcc.aplicacao.domain.Usuario;

public interface BuscarInformacoesUsuarioService {
    Usuario buscarInformacoesUsuario(String authorization);
}
