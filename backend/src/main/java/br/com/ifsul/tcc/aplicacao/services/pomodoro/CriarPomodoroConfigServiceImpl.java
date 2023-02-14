package br.com.ifsul.tcc.aplicacao.services.pomodoro;

import br.com.ifsul.tcc.aplicacao.domain.PomodoroConfig;
import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.repository.PomodoroConfigRepository;
import br.com.ifsul.tcc.aplicacao.services.usuario.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriarPomodoroConfigServiceImpl implements CriarPomodoroConfigService {

    @Autowired
    PomodoroConfigRepository pomodoroConfigRepository;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Override
    public PomodoroConfig criarPomodoroConfig(PomodoroConfig request) {
        Usuario usuario = usuarioAutenticadoService.get();

        request.setUsuario(usuario);
        request.setIsVisivel(true);

        return pomodoroConfigRepository.save(request);
    }
}
