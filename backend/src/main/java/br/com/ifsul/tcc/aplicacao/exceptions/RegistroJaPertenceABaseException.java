package br.com.ifsul.tcc.aplicacao.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegistroJaPertenceABaseException extends RuntimeException {

    public RegistroJaPertenceABaseException(String mensagem) {
        super(mensagem);
    }
}
