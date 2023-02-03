package br.com.ifsul.tcc.aplicacao.services.tarefa;

import br.com.ifsul.tcc.aplicacao.ENUM.CATEGORIA;
import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.repository.TarefaRepository;
import br.com.ifsul.tcc.aplicacao.represetation.response.TarefaAgrupadaResponse;
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
    public TarefaAgrupadaResponse buscarTarefas(String token) {
        Usuario usuario = usuarioAutenticadoService.get(token);

        List<Tarefa> tarefas = tarefaRepository.buscarTarefasOrdenadasPorPrioridade(usuario.getId());

        TarefaAgrupadaResponse tarefaAgrupadaResponse = new TarefaAgrupadaResponse();

        //TODO
        //passar isso pro mapper

        tarefaAgrupadaResponse.setUrgenteImportante(tarefas.stream().filter(tarefa -> tarefa.getPrioridade() == CATEGORIA.URGENTE_IMPORTANTE).toList());

        tarefaAgrupadaResponse.setNaoUrgenteImportante(tarefas.stream().filter(tarefa -> tarefa.getPrioridade() == CATEGORIA.NAO_URGENTE_IMPORTANTE).toList());

        tarefaAgrupadaResponse.setUrgenteNaoImportante(tarefas.stream().filter(tarefa -> tarefa.getPrioridade() == CATEGORIA.URGENTE_NAO_IMPORTANTE).toList());

        tarefaAgrupadaResponse.setNaoUrgenteNaoImportante(tarefas.stream().filter(tarefa -> tarefa.getPrioridade() == CATEGORIA.NAO_URGENTE_NAO_IMPORTANTE).toList());

        return tarefaAgrupadaResponse;

    }
}
