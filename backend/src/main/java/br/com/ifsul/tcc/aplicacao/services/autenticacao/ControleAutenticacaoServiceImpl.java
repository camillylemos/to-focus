package br.com.ifsul.tcc.aplicacao.services.autenticacao;

import br.com.ifsul.tcc.aplicacao.domain.*;
import br.com.ifsul.tcc.aplicacao.exceptions.RegistroNaoEncontradoException;
import br.com.ifsul.tcc.aplicacao.repository.AlbumRepository;
import br.com.ifsul.tcc.aplicacao.repository.AutenticacaoRepository;
import br.com.ifsul.tcc.aplicacao.repository.ColecaoUsuarioRepository;
import br.com.ifsul.tcc.aplicacao.repository.FiguraRepository;
import br.com.ifsul.tcc.aplicacao.represetation.response.Colecao;
import br.com.ifsul.tcc.aplicacao.represetation.response.GamificacaoResponse;
import br.com.ifsul.tcc.aplicacao.services.usuario.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ControleAutenticacaoServiceImpl implements ControleAutenticacaoService {

    @Autowired
    AutenticacaoRepository autenticacaoRepository;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    FiguraRepository figuraRepository;

    @Autowired
    ColecaoUsuarioRepository colecaoUsuarioRepository;

    @Override
    public GamificacaoResponse controleAutenticacao() {
        Usuario usuario = usuarioAutenticadoService.get();

        Autenticacao autenticacao = autenticacaoRepository.findByUsuarioIdUltimoRegistro(usuario.getId());

        Long qntsAutenticacoes = autenticacaoRepository.qtsAutenticacoes(usuario.getId());

        if (autenticacao == null || !autenticacao.getDataAutenticacao().isEqual(LocalDate.now())) {
            Autenticacao novaAutenticacao = new Autenticacao(LocalDate.now(), LocalTime.now(), usuario);

            autenticacaoRepository.save(novaAutenticacao);

            return retornoPremio(usuario, qntsAutenticacoes);

        } else {
            throw new RegistroNaoEncontradoException("Autenticação já realizada hoje");
        }
    }

    //TODO
    //PASSAR PARA SERVICE DE GAMIFICACAO
    private GamificacaoResponse retornoPremio(Usuario usuario, Long qntsAutenticacoes) {
        List<Album> listaAlbuns = albumRepository.findAll();

        List<Figura> listaFigura = figuraRepository.findAll();

        if (qntsAutenticacoes == 1) {
            Album album = listaAlbuns.get(0);

            Figura figura = listaFigura.get(0);

            ColecaoUsuario primeiraFigura = new ColecaoUsuario(usuario, album, figura);

            colecaoUsuarioRepository.save(primeiraFigura);

            List<ColecaoUsuario> listaFiguras = colecaoUsuarioRepository.findAllByUsuarioId(usuario.getId());

            Map<String, List<Figura>> figurasPorAlbum = listaFiguras.stream().collect(Collectors.groupingBy(c -> c.getAlbum().getNome(), Collectors.mapping(c -> c.getFigura(), Collectors.toList())));

            List<Colecao> novosAlbuns = figurasPorAlbum.entrySet().stream()
                    .map(e -> new Colecao(e.getKey(), e.getValue()))
                    .collect(Collectors.toList());

            return new GamificacaoResponse("Primeira autenticacao", novosAlbuns);

        }

        if (qntsAutenticacoes % 2 == 0) {
            List<ColecaoUsuario> listaFigurasUsuario = colecaoUsuarioRepository.findAllByUsuarioId(usuario.getId());

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

            List<ColecaoUsuario> listaFiguras = colecaoUsuarioRepository.findAllByUsuarioId(usuario.getId());

            Map<String, List<Figura>> figurasPorAlbum = listaFiguras.stream().collect(Collectors.groupingBy(c -> c.getAlbum().getNome(), Collectors.mapping(c -> c.getFigura(), Collectors.toList())));

            List<Colecao> novosAlbuns = figurasPorAlbum.entrySet().stream()
                    .map(e -> new Colecao(e.getKey(), e.getValue()))
                    .collect(Collectors.toList());

            return new GamificacaoResponse("Você recebeu uma nova figura por estar a " + qntsAutenticacoes + " dias consecutivos mantendo o foco conosco!", novosAlbuns);
        }

        return null;
    }
}
