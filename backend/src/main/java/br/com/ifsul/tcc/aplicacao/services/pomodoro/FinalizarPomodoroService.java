package br.com.ifsul.tcc.aplicacao.services.pomodoro;

import br.com.ifsul.tcc.aplicacao.represetation.response.MensagemResponse;

public interface FinalizarPomodoroService {
    MensagemResponse finalizarPomodoro(Integer id);
}
