package br.com.ifsul.tcc.aplicacao.services.pomodoro;

import br.com.ifsul.tcc.aplicacao.domain.PomodoroConfig;
import br.com.ifsul.tcc.aplicacao.repository.PomodoroConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriarPomodoroConfigServiceImpl implements CriarPomodoroConfigService {

    @Autowired
    PomodoroConfigRepository pomodoroConfigRepository;

    @Override
    public PomodoroConfig criarPomodoroConfig(PomodoroConfig request) {
        PomodoroConfig pomodoroConfig = new PomodoroConfig(request.getNomeCategoria(), request.getTempoFoco(), request.getTempoIntervaloCurto(), request.getTempoIntervaloLongo());

        return pomodoroConfigRepository.save(pomodoroConfig);
    }
}
