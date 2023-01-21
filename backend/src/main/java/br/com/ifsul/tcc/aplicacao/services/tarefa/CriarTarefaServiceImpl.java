package br.com.ifsul.tcc.aplicacao.services.tarefa;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.mapper.TarefaMapper;
import br.com.ifsul.tcc.aplicacao.repository.TarefaRepository;
import br.com.ifsul.tcc.aplicacao.represetation.request.TarefaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriarTarefaServiceImpl implements CriarTarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    TarefaMapper tarefaMapper;

    @Override
    public Tarefa criarTarefa(TarefaRequest request) {
        Tarefa tarefa = tarefaMapper.toDomain(request);


        return tarefaRepository.save(tarefa);
    }

}
