package br.com.ifsul.tcc.aplicacao.controller;

import br.com.ifsul.tcc.aplicacao.represetation.response.GamificacaoResponse;
import br.com.ifsul.tcc.aplicacao.services.gamificacao.BuscarColecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("colecao")
public class ColecaoController {
    @Autowired
    BuscarColecaoService buscarColecaoService;

    @GetMapping()
    public GamificacaoResponse buscarColecao() {
        return buscarColecaoService.buscarColecao();
    }
}
