package br.com.ifsul.tcc.aplicacao.services.pomodoro;

import br.com.ifsul.tcc.aplicacao.domain.Pomodoro;
import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.repository.PomodoroRepository;
import br.com.ifsul.tcc.aplicacao.represetation.response.MensagemResponse;
import br.com.ifsul.tcc.aplicacao.services.usuario.UsuarioAutenticadoService;
import br.com.ifsul.tcc.aplicacao.validator.PomodoroValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinalizarPomodoroServiceImpl implements FinalizarPomodoroService {

    @Autowired
    PomodoroRepository pomodoroRepository;

    @Autowired
    PomodoroValidator pomodoroValidator;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Override
    public MensagemResponse finalizarPomodoro(Integer id) {
        Usuario usuario = usuarioAutenticadoService.get();

        Pomodoro pomodoro = pomodoroRepository.findByIdAndUsuarioId(id, usuario.getId());

        pomodoroValidator.accept(pomodoro);

        pomodoro.setEstaFinalizado(true);
        pomodoroRepository.save(pomodoro);

        return controleFinalizacaoPomodoro(usuario);
    }

    private MensagemResponse controleFinalizacaoPomodoro(Usuario usuario) {
        long qntsPomodoros = pomodoroRepository.qntsPomododorosFinalizados(usuario.getId(), true);

        if (qntsPomodoros == 1) {
            return new MensagemResponse("Primeiro pomodoro finalizado");
        }

        if (qntsPomodoros % 5 == 0) {
            return new MensagemResponse("pomodoro finalizado " + qntsPomodoros);
        }

        return null;
    }

}
