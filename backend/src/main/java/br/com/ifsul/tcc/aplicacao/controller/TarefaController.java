package br.com.ifsul.tcc.aplicacao.controller;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.represetation.request.AlterarTarefaRequest;
import br.com.ifsul.tcc.aplicacao.represetation.request.TarefaRequest;
import br.com.ifsul.tcc.aplicacao.represetation.response.TarefaResponse;
import br.com.ifsul.tcc.aplicacao.services.tarefa.AlterarTarefaService;
import br.com.ifsul.tcc.aplicacao.services.tarefa.BuscarTarefasService;
import br.com.ifsul.tcc.aplicacao.services.tarefa.CriarTarefaService;
import br.com.ifsul.tcc.aplicacao.services.tarefa.DeletarTarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public TarefaResponse buscarTarefas() {
        return buscarTarefasService.buscarTarefas();
    }


    @PostMapping()
    public String criarTarefa(@RequestBody @Valid TarefaRequest tarefaRequest) {
        return criarTarefaService.criarTarefa(tarefaRequest);
    }

    @PutMapping("/{id}")
    public Tarefa alterarTarefa(@RequestBody @Valid AlterarTarefaRequest alterarTarefaRequest, @PathVariable Integer id) {
        return alterarTarefaService.alterarTarefa(alterarTarefaRequest, id);
    }

    @DeleteMapping("/{id}")
    public String deletarTarefa(@PathVariable Integer id) {
        return deletarTarefaService.deletarTarefa(id);
    }
}
