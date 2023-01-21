package br.com.ifsul.tcc.aplicacao.validator;

import br.com.ifsul.tcc.aplicacao.domain.Pomodoro;
import br.com.ifsul.tcc.aplicacao.exceptions.RegistroNaoEncontradoException;
import org.springframework.stereotype.Component;

@Component
public class PomodoroValidator {
    public void accept(Pomodoro pomodoro) {
        if (pomodoro == null) {
            throw new RegistroNaoEncontradoException("Pomodoro inexistente");
        }
    }
}
