package br.com.ifsul.tcc.aplicacao.services.tarefa;

import br.com.ifsul.tcc.aplicacao.represetation.response.TarefaAgrupadaResponse;

public interface BuscarTarefasService {
    TarefaAgrupadaResponse buscarTarefas(String token);
}
