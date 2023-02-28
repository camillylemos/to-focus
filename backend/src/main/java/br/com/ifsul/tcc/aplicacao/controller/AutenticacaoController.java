package br.com.ifsul.tcc.aplicacao.controller;

import br.com.ifsul.tcc.aplicacao.represetation.response.GamificacaoResponse;
import br.com.ifsul.tcc.aplicacao.services.autenticacao.ControleAutenticacaoService;
import br.com.ifsul.tcc.aplicacao.services.autenticacao.DiasAutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("autenticacao")
public class AutenticacaoController {

    @Autowired
    ControleAutenticacaoService controleAutenticacaoService;

    @Autowired
    DiasAutenticacaoService diasAutenticacaoService;

    @GetMapping("/controle")
    public GamificacaoResponse controleAutenticacao() {
        return controleAutenticacaoService.controleAutenticacao();
    }

    @GetMapping()
    public Long diasAutenticacao() {
        return diasAutenticacaoService.diasAutenticacao();
    }
}
