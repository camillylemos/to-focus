package br.com.ifsul.tcc.aplicacao.services.autenticacao;

import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.repository.AutenticacaoRepository;
import br.com.ifsul.tcc.aplicacao.services.usuario.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiasAutenticacaoServiceImpl implements DiasAutenticacaoService {

    @Autowired
    AutenticacaoRepository autenticacaoRepository;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Override
    public Long diasAutenticacao() {
        Usuario usuario = usuarioAutenticadoService.get();

        return autenticacaoRepository.qtsAutenticacoes(usuario.getId());
    }
}
