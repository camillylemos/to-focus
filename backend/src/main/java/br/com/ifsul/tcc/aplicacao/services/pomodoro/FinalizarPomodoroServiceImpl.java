package br.com.ifsul.tcc.aplicacao.services.pomodoro;

import br.com.ifsul.tcc.aplicacao.domain.*;
import br.com.ifsul.tcc.aplicacao.repository.AlbumRepository;
import br.com.ifsul.tcc.aplicacao.repository.ColecaoUsuarioRepository;
import br.com.ifsul.tcc.aplicacao.repository.FiguraRepository;
import br.com.ifsul.tcc.aplicacao.repository.PomodoroRepository;
import br.com.ifsul.tcc.aplicacao.represetation.response.GamificacaoResponse;
import br.com.ifsul.tcc.aplicacao.services.usuario.UsuarioAutenticadoService;
import br.com.ifsul.tcc.aplicacao.validator.PomodoroValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinalizarPomodoroServiceImpl implements FinalizarPomodoroService {

    @Autowired
    PomodoroRepository pomodoroRepository;

    @Autowired
    PomodoroValidator pomodoroValidator;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    FiguraRepository figuraRepository;

    @Autowired
    ColecaoUsuarioRepository colecaoUsuarioRepository;

    @Override
    public GamificacaoResponse finalizarPomodoro(Integer id) {
        Usuario usuario = usuarioAutenticadoService.get();

        Pomodoro pomodoro = pomodoroRepository.findByIdAndUsuarioId(id, usuario.getId());

        pomodoroValidator.accept(pomodoro);

        pomodoro.setEstaFinalizado(true);
        pomodoroRepository.save(pomodoro);

        return controleFinalizacaoPomodoro(usuario);
    }

    private GamificacaoResponse controleFinalizacaoPomodoro(Usuario usuario) {
        long qntsPomodoros = pomodoroRepository.qntsPomododorosFinalizados(usuario.getId(), true);

        if (qntsPomodoros == 1) {
            List<ColecaoUsuario> listaFiguras = buscarAlbumFiguraAtual(usuario);

            return new GamificacaoResponse("Primeiro pomodoro finalizado", listaFiguras);
        }

        if (qntsPomodoros % 5 == 0) {
            List<ColecaoUsuario> listaFiguras = buscarAlbumFiguraAtual(usuario);

            return new GamificacaoResponse("pomodoro finalizado " + qntsPomodoros, listaFiguras);
        }

        return null;
    }

    private List<ColecaoUsuario> buscarAlbumFiguraAtual(Usuario usuario) {
        List<ColecaoUsuario> listaFigurasUsuario = colecaoUsuarioRepository.findAllByUsuarioId(usuario.getId());

        List<Album> listaAlbuns = albumRepository.findAll();

        List<Figura> listaFigura = figuraRepository.findAll();

        Album albumAtual = new Album();
        Figura figuraAtual = new Figura();

        ColecaoUsuario ultimaFiguraUsuario = listaFigurasUsuario.get(listaFigurasUsuario.size() - 1);

        for (int i = 0; i < listaAlbuns.size(); i++) {
            if (ultimaFiguraUsuario.getAlbum() == listaAlbuns.get(i)) {
                if (ultimaFiguraUsuario.getFigura() != listaAlbuns.get(i).getFiguras().get(listaAlbuns.get(i).getFiguras().size() - 1)) {
                    albumAtual = listaAlbuns.get(i);
                    figuraAtual = listaFigura.get(listaFigura.indexOf(ultimaFiguraUsuario.getFigura()) + 1);
                } else {
                    albumAtual = listaAlbuns.get(ultimaFiguraUsuario.getAlbum().getId());
                    figuraAtual = albumAtual.getFiguras().get(0);
                }
            }
        }

        colecaoUsuarioRepository.save(new ColecaoUsuario(usuario, albumAtual, figuraAtual));

        return colecaoUsuarioRepository.findAllByUsuarioId(usuario.getId());
    }

}
