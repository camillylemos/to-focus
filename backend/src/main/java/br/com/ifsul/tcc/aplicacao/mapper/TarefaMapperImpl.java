package br.com.ifsul.tcc.aplicacao.mapper;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.represetation.request.TarefaRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TarefaMapperImpl implements TarefaMapper {

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Tarefa toDomain(TarefaRequest request, Usuario usuario) {
        Tarefa tarefa = new Tarefa(request.getTitulo(), request.getDescricao(), false, LocalDateTime.now(), null, request.getPrioridade(), usuario);

        return tarefa;
    }

}
