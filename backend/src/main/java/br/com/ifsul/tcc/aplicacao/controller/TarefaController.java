package br.com.ifsul.tcc.aplicacao.controller;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.represetation.request.AlterarTarefaRequest;
import br.com.ifsul.tcc.aplicacao.represetation.request.TarefaRequest;
import br.com.ifsul.tcc.aplicacao.represetation.response.TarefaResponse;
import br.com.ifsul.tcc.aplicacao.services.tarefa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("tarefa")
public class TarefaController {

    @Autowired
    BuscarTarefasMatrizService buscarTarefasMatrizService;

    @Autowired
    BuscarTarefasService buscarTarefasService;

    @Autowired
    CriarTarefaService criarTarefaService;

    @Autowired
    AlterarTarefaService alterarTarefaService;

    @Autowired
    DeletarTarefaService deletarTarefaService;

    @GetMapping("/matriz")
    public TarefaResponse buscarTarefasMatriz() {
        return buscarTarefasMatrizService.buscarTarefas();
    }

    @GetMapping()
    public List<TarefaResponse> buscarTarefas() {
        return buscarTarefasService.buscarTarefas();
    }

    @PostMapping()
    public Tarefa criarTarefa(@RequestBody @Valid TarefaRequest tarefaRequest) {
        return criarTarefaService.criarTarefa(tarefaRequest);
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
