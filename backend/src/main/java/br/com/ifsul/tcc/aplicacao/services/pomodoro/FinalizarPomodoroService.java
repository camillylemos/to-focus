package br.com.ifsul.tcc.aplicacao.services.pomodoro;

import br.com.ifsul.tcc.aplicacao.represetation.response.GamificacaoResponse;

public interface FinalizarPomodoroService {
    GamificacaoResponse finalizarPomodoro(Integer id);
}
