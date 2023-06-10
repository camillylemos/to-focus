package br.com.ifsul.tcc.aplicacao.mapper;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.represetation.request.TarefaRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TarefaMapperImpl implements TarefaMapper {
    @Override
    public Tarefa toDomain(TarefaRequest request, Usuario usuario) {
        Tarefa tarefa = new Tarefa(request.getTitulo(), request.getDescricao(), false, LocalDateTime.now(), null, request.getPrioridade(), usuario, true);

        return tarefa;
    }
}
