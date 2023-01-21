package br.com.ifsul.tcc.aplicacao.services.tarefa;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.represetation.request.TarefaRequest;

public interface CriarTarefaService {


    Tarefa criarTarefa(TarefaRequest tarefaRequest);
}
