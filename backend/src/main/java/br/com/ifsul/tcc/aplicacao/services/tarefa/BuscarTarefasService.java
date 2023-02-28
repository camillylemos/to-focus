package br.com.ifsul.tcc.aplicacao.services.tarefa;

import br.com.ifsul.tcc.aplicacao.represetation.response.TarefaResponse;

import java.util.List;

public interface BuscarTarefasService {
    List<TarefaResponse> buscarTarefas();
}
