package br.com.ifsul.tcc.aplicacao.services.tarefa;

import br.com.ifsul.tcc.aplicacao.represetation.request.TarefaRequest;

public interface CriarTarefaService {


    void criarTarefa(TarefaRequest tarefaRequest, String token);
}
