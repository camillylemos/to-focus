package br.com.ifsul.tcc.aplicacao.controller;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.represetation.request.AlterarTarefaRequest;
import br.com.ifsul.tcc.aplicacao.represetation.request.TarefaRequest;
import br.com.ifsul.tcc.aplicacao.represetation.response.TarefaAgrupadaResponse;
import br.com.ifsul.tcc.aplicacao.services.tarefa.AlterarTarefaService;
import br.com.ifsul.tcc.aplicacao.services.tarefa.BuscarTarefasService;
import br.com.ifsul.tcc.aplicacao.services.tarefa.CriarTarefaService;
import br.com.ifsul.tcc.aplicacao.services.tarefa.DeletarTarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(allowedHeaders = {"Authorization", "Origin"})
@RestController
@RequestMapping("tarefa")
public class TarefaController {

    @Autowired
    BuscarTarefasService buscarTarefasService;

    @Autowired
    CriarTarefaService criarTarefaService;

    @Autowired
    AlterarTarefaService alterarTarefaService;

    @Autowired
    DeletarTarefaService deletarTarefaService;

    @GetMapping()
    public TarefaAgrupadaResponse buscarTarefas(@RequestHeader String authorization) {
        return buscarTarefasService.buscarTarefas(authorization);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void criarTarefa(@RequestBody @Valid TarefaRequest tarefaRequest, @RequestHeader String authorization) {
        criarTarefaService.criarTarefa(tarefaRequest, authorization);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public Tarefa alterarTarefa(@RequestBody @Valid AlterarTarefaRequest alterarTarefaRequest, @PathVariable Integer id) {
        return alterarTarefaService.alterarTarefa(alterarTarefaRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable Integer id) {
        deletarTarefaService.deletarTarefa(id);
    }
}
