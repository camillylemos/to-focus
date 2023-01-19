package br.com.ifsul.tcc.aplicacao.services.tarefa;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.repository.TarefaRepository;
import br.com.ifsul.tcc.aplicacao.represetation.validator.TarefaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletarTarefaServiceImpl implements DeletarTarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    TarefaValidator tarefaValidator;

    @Override
    public String deletarTarefa(Integer id) {

        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);

        tarefaValidator.accept(tarefa);

        tarefaRepository.delete(tarefa);

        //TODO
        //mensagem para todos os endpoints

        String message = "deu certo";
        return message;
    }
}
