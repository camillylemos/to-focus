package br.com.ifsul.tcc.aplicacao.services.pomodoro;

import br.com.ifsul.tcc.aplicacao.domain.Pomodoro;
import br.com.ifsul.tcc.aplicacao.domain.PomodoroConfig;
import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.repository.PomodoroConfigRepository;
import br.com.ifsul.tcc.aplicacao.repository.PomodoroRepository;
import br.com.ifsul.tcc.aplicacao.services.usuario.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IniciarPomodoroServiceImpl implements IniciarPomodoroService {

    @Autowired
    PomodoroRepository pomodoroRepository;

    @Autowired
    PomodoroConfigRepository pomodoroConfigRepository;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Override
    public Pomodoro iniciarPomodoro(Integer id, String token) {
        Usuario usuario = usuarioAutenticadoService.get(token);

        PomodoroConfig pomodoroConfig = pomodoroConfigRepository.findByIdAndUsuarioId(id, usuario.getId()).orElse(null);

        Pomodoro pomodoro = new Pomodoro(false, pomodoroConfig, usuario);

        return pomodoroRepository.save(pomodoro);

    }
}
