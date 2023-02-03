package br.com.ifsul.tcc.aplicacao.services.pomodoro;

import br.com.ifsul.tcc.aplicacao.domain.Pomodoro;

public interface IniciarPomodoroService {
    Pomodoro iniciarPomodoro(Integer id, String token);
}
