package br.com.ifsul.tcc.aplicacao.controller;

import br.com.ifsul.tcc.aplicacao.represetation.response.EstatisticaResponse;
import br.com.ifsul.tcc.aplicacao.services.estatistica.BuscarEstatisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("estatistica")
public class EstatisticaController {

    @Autowired
    BuscarEstatisticaService buscarEstatisticaService;

    @GetMapping()
    public EstatisticaResponse buscarEstatistica() {
        return buscarEstatisticaService.buscarEstatistica();
    }
}
