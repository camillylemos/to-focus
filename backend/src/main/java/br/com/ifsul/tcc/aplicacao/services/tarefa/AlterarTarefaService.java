package br.com.ifsul.tcc.aplicacao.services.tarefa;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.exceptions.RegistroNaoEncontradoException;
import br.com.ifsul.tcc.aplicacao.represetation.request.AlterarTarefaRequest;

public interface AlterarTarefaService {
    Tarefa alterarTarefa(AlterarTarefaRequest alterarTarefaRequest, Integer id) throws RegistroNaoEncontradoException;
}
