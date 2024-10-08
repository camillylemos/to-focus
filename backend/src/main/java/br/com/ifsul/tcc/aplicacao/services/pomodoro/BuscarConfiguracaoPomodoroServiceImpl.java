package br.com.ifsul.tcc.aplicacao.services.pomodoro;

import br.com.ifsul.tcc.aplicacao.domain.PomodoroConfig;
import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.repository.PomodoroConfigRepository;
import br.com.ifsul.tcc.aplicacao.services.usuario.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarConfiguracaoPomodoroServiceImpl implements BuscarConfiguracaoPomodoroService {

    @Autowired
    PomodoroConfigRepository pomodoroConfigRepository;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Override
    public List<PomodoroConfig> buscarConfiguracaoPomodoro() {
        Usuario usuario = usuarioAutenticadoService.get();

        return pomodoroConfigRepository.findByUsuarioId(usuario.getId());
    }
}
