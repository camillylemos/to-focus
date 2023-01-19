package br.com.ifsul.tcc.aplicacao.represetation.validator;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.exceptions.RegistroNaoEncontradoException;
import org.springframework.stereotype.Component;

@Component
public class TarefaValidator {
    public void accept(Tarefa tarefa) {
        if (tarefa == null) {
            throw new RegistroNaoEncontradoException("Tarefa inexistente");
        }
    }
}
