package br.com.ifsul.tcc.aplicacao.services.usuario;

import br.com.ifsul.tcc.aplicacao.represetation.request.LogarUsuarioRequest;
import br.com.ifsul.tcc.aplicacao.represetation.response.TokenResponse;

public interface LogarUsuarioService {
    TokenResponse logarUsuario(LogarUsuarioRequest request);
}
