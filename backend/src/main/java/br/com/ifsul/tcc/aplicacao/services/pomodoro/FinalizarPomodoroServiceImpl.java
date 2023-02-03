package br.com.ifsul.tcc.aplicacao.services.pomodoro;

import br.com.ifsul.tcc.aplicacao.domain.Pomodoro;
import br.com.ifsul.tcc.aplicacao.repository.PomodoroRepository;
import br.com.ifsul.tcc.aplicacao.validator.PomodoroValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinalizarPomodoroServiceImpl implements FinalizarPomodoroService {

    @Autowired
    PomodoroRepository pomodoroRepository;

    @Autowired
    PomodoroValidator pomodoroValidator;

    @Override
    public void finalizarPomodoro(Integer id) {
        Pomodoro pomodoro = pomodoroRepository.findById(id).orElse(null);

        pomodoroValidator.accept(pomodoro);
        pomodoro.setEstaFinalizado(true);

        pomodoroRepository.save(pomodoro);
    }
}
