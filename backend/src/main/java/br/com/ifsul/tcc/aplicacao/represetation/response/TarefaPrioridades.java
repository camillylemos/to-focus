package br.com.ifsul.tcc.aplicacao.represetation.response;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;

import java.util.List;

public class TarefaPrioridades {

    List<Tarefa> urgenteImportante;

    List<Tarefa> naoUrgenteImportante;

    List<Tarefa> urgenteNaoImportante;

    List<Tarefa> naoUrgenteNaoImportante;
}
