package br.com.ifsul.tcc.aplicacao.services.pomodoro;

import br.com.ifsul.tcc.aplicacao.domain.PomodoroConfig;
import br.com.ifsul.tcc.aplicacao.repository.PomodoroConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarConfiguracaoPomodoroServiceImpl implements BuscarConfiguracaoPomodoroService {

    @Autowired
    PomodoroConfigRepository pomodoroConfigRepository;

    @Override
    public List<PomodoroConfig> buscarConfiguracaoPomodoro() {
        return pomodoroConfigRepository.findAll();
    }
}
