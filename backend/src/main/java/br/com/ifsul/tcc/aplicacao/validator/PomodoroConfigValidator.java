package br.com.ifsul.tcc.aplicacao.validator;

import br.com.ifsul.tcc.aplicacao.domain.PomodoroConfig;
import br.com.ifsul.tcc.aplicacao.exceptions.RegistroNaoEncontradoException;
import org.springframework.stereotype.Component;

@Component
public class PomodoroConfigValidator {
    public void accept(PomodoroConfig pomodoroConfig) {
        if (pomodoroConfig == null) {
            throw new RegistroNaoEncontradoException("Configuração de pomodoro inexistente");
        }
    }
}
