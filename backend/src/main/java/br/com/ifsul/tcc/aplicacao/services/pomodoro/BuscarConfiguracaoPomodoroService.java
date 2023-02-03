package br.com.ifsul.tcc.aplicacao.services.pomodoro;

import br.com.ifsul.tcc.aplicacao.domain.PomodoroConfig;

import java.util.List;

public interface BuscarConfiguracaoPomodoroService {

    List<PomodoroConfig> buscarConfiguracaoPomodoro(String token);
}
