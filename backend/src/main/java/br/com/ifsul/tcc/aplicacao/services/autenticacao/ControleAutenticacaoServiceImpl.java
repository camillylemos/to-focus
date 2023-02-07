package br.com.ifsul.tcc.aplicacao.services.autenticacao;

import br.com.ifsul.tcc.aplicacao.domain.Autenticacao;
import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.exceptions.RegistroNaoEncontradoException;
import br.com.ifsul.tcc.aplicacao.repository.AutenticacaoRepository;
import br.com.ifsul.tcc.aplicacao.represetation.response.MensagemResponse;
import br.com.ifsul.tcc.aplicacao.services.usuario.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ControleAutenticacaoServiceImpl implements ControleAutenticacaoService {

    @Autowired
    AutenticacaoRepository autenticacaoRepository;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Override
    public MensagemResponse controleAutenticacao() {
        Usuario usuario = usuarioAutenticadoService.get();

        Autenticacao autenticacao = autenticacaoRepository.findByUsuarioIdUltimoRegistro(usuario.getId());

        if (autenticacao == null || !autenticacao.getDataAutenticacao().isEqual(LocalDate.now())) {
            Autenticacao novaAutenticacao = new Autenticacao(LocalDate.now(), LocalTime.now(), usuario);

            autenticacaoRepository.save(novaAutenticacao);

            return retornoPremio(usuario);

        } else {
            throw new RegistroNaoEncontradoException("Autenticação já realizada");
        }
    }

    //TODO
    //PASSAR PARA SERVICE DE GAMIFICACAO
    private MensagemResponse retornoPremio(Usuario usuario) {
        Long qntsAutenticacoes = autenticacaoRepository.qtsAutenticacoes(usuario.getId());

        if (qntsAutenticacoes == 1) {
            return new MensagemResponse("Primeira autenticacao");
        }

        if (qntsAutenticacoes % 2 == 0) {
            return new MensagemResponse("Autenticacao numero " + qntsAutenticacoes);
        }

        return null;
    }
}
