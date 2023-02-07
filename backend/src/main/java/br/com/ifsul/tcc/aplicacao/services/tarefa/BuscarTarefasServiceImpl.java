package br.com.ifsul.tcc.aplicacao.services.tarefa;

import br.com.ifsul.tcc.aplicacao.ENUM.CATEGORIA;
import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.repository.TarefaRepository;
import br.com.ifsul.tcc.aplicacao.represetation.response.TarefaResponse;
import br.com.ifsul.tcc.aplicacao.services.usuario.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarTarefasServiceImpl implements BuscarTarefasService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Override
    public TarefaResponse buscarTarefas() {
        Usuario usuario = usuarioAutenticadoService.get();

        List<Tarefa> tarefas = tarefaRepository.buscarTarefasOrdenadasPorPrioridade(usuario.getId());

        TarefaResponse tarefaResponse = new TarefaResponse();

        //TODO
        //passar isso pro mapper

        tarefaResponse.setUrgenteImportante(tarefas.stream().filter(tarefa -> tarefa.getPrioridade() == CATEGORIA.URGENTE_IMPORTANTE).toList());

        tarefaResponse.setNaoUrgenteImportante(tarefas.stream().filter(tarefa -> tarefa.getPrioridade() == CATEGORIA.NAO_URGENTE_IMPORTANTE).toList());

        tarefaResponse.setUrgenteNaoImportante(tarefas.stream().filter(tarefa -> tarefa.getPrioridade() == CATEGORIA.URGENTE_NAO_IMPORTANTE).toList());

        tarefaResponse.setNaoUrgenteNaoImportante(tarefas.stream().filter(tarefa -> tarefa.getPrioridade() == CATEGORIA.NAO_URGENTE_NAO_IMPORTANTE).toList());

        return tarefaResponse;

    }
}
