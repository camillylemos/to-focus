package br.com.ifsul.tcc.aplicacao.services.gamificacao;

import br.com.ifsul.tcc.aplicacao.domain.ColecaoUsuario;
import br.com.ifsul.tcc.aplicacao.domain.Figura;
import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.repository.ColecaoUsuarioRepository;
import br.com.ifsul.tcc.aplicacao.represetation.response.Colecao;
import br.com.ifsul.tcc.aplicacao.represetation.response.GamificacaoResponse;
import br.com.ifsul.tcc.aplicacao.services.usuario.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuscarColecaoServiceImpl implements BuscarColecaoService {

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    ColecaoUsuarioRepository colecaoUsuarioRepository;

    @Override
    public GamificacaoResponse buscarColecao() {
        Usuario usuario = usuarioAutenticadoService.get();

        List<ColecaoUsuario> listaFiguras = colecaoUsuarioRepository.findAllByUsuarioId(usuario.getId());

        Map<String, List<Figura>> figurasPorAlbum = listaFiguras.stream().collect(Collectors.groupingBy(c -> c.getAlbum().getNome(), Collectors.mapping(c -> c.getFigura(), Collectors.toList())));

        List<Colecao> novosAlbuns = figurasPorAlbum.entrySet().stream()
                .map(e -> new Colecao(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        return new GamificacaoResponse(null, novosAlbuns);
    }
}
