package br.com.ifsul.tcc.aplicacao.services.pomodoro;

import br.com.ifsul.tcc.aplicacao.domain.Pomodoro;
import br.com.ifsul.tcc.aplicacao.domain.PomodoroConfig;
import br.com.ifsul.tcc.aplicacao.repository.PomodoroConfigRepository;
import br.com.ifsul.tcc.aplicacao.repository.PomodoroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IniciarPomodoroServiceImpl implements IniciarPomodoroService {

    @Autowired
    PomodoroRepository pomodoroRepository;

    @Autowired
    PomodoroConfigRepository pomodoroConfigRepository;

    @Override
    public void iniciarPomodoro(Integer id) {

        //TODO
        //response com 201
        //response com objeto que foi criado

        PomodoroConfig pomodoroConfig = pomodoroConfigRepository.findById(id).orElse(null);

        Pomodoro pomodoro = new Pomodoro(null, false, pomodoroConfig);

        pomodoroRepository.save(pomodoro);
    }
}
