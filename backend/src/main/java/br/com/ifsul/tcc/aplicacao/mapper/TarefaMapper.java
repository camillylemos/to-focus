package br.com.ifsul.tcc.aplicacao.mapper;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.represetation.request.TarefaRequest;

public interface TarefaMapper {
    Tarefa toDomain(TarefaRequest request, Usuario usuario);
}
