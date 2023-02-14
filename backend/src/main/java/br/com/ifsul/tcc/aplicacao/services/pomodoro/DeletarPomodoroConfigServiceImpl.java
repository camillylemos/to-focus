package br.com.ifsul.tcc.aplicacao.services.pomodoro;

import br.com.ifsul.tcc.aplicacao.domain.PomodoroConfig;
import br.com.ifsul.tcc.aplicacao.repository.PomodoroConfigRepository;
import br.com.ifsul.tcc.aplicacao.validator.PomodoroConfigValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletarPomodoroConfigServiceImpl implements DeletarPomodoroConfigService {

    @Autowired
    PomodoroConfigRepository pomodoroConfigRepository;

    @Autowired
    PomodoroConfigValidator pomodoroConfigValidator;

    @Override
    public void deletarPomodoroConfig(Integer id) {
        PomodoroConfig pomodoroConfig = pomodoroConfigRepository.findById(id).orElse(null);

        pomodoroConfigValidator.accept(pomodoroConfig);

        pomodoroConfig.setIsVisivel(false);

        pomodoroConfigRepository.save(pomodoroConfig);
    }
}
