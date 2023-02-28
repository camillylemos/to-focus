package br.com.ifsul.tcc.aplicacao.services.tarefa;

import br.com.ifsul.tcc.aplicacao.ENUM.CATEGORIA;
import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.repository.TarefaRepository;
import br.com.ifsul.tcc.aplicacao.represetation.response.TarefaResponse;
import br.com.ifsul.tcc.aplicacao.services.usuario.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarTarefasServiceImpl implements BuscarTarefasService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Override
    public List<TarefaResponse> buscarTarefas() {
        Usuario usuario = usuarioAutenticadoService.get();

        List<Tarefa> tarefas = tarefaRepository.buscarTarefasOrdenadasPorPrioridade(usuario.getId());

        TarefaResponse tarefaRealizada = new TarefaResponse();

        TarefaResponse tarefaNaoRealizada = new TarefaResponse();

        List<TarefaResponse> retorno = new ArrayList<>();

        //TODO
        //passar isso pro mapper

        List<Tarefa> urgenteImportante = tarefas.stream().filter(tarefa -> tarefa.getPrioridade() == CATEGORIA.URGENTE_IMPORTANTE)
                .collect(Collectors.toList());

        List<Tarefa> naoUrgenteImportante = tarefas.stream().filter(tarefa -> tarefa.getPrioridade() == CATEGORIA.NAO_URGENTE_IMPORTANTE)
                .collect(Collectors.toList());

        List<Tarefa> urgenteNaoImportante = tarefas.stream().filter(tarefa -> tarefa.getPrioridade() == CATEGORIA.URGENTE_NAO_IMPORTANTE)
                .collect(Collectors.toList());

        List<Tarefa> naoUrgenteNaoImportante = tarefas.stream().filter(tarefa -> tarefa.getPrioridade() == CATEGORIA.NAO_URGENTE_NAO_IMPORTANTE)
                .collect(Collectors.toList());

        tarefaRealizada.setUrgenteImportante(urgenteImportante.stream().filter(t -> t.isEstaRealizado() == true)
                .collect(Collectors.toList()));

        tarefaRealizada.setNaoUrgenteImportante(naoUrgenteImportante.stream().filter(t -> t.isEstaRealizado() == true)
                .collect(Collectors.toList()));

        tarefaRealizada.setUrgenteNaoImportante(urgenteNaoImportante.stream().filter(t -> t.isEstaRealizado() == true)
                .collect(Collectors.toList()));

        tarefaRealizada.setNaoUrgenteNaoImportante(naoUrgenteNaoImportante.stream().filter(t -> t.isEstaRealizado() == true)
                .collect(Collectors.toList()));

        //nao realizada

        tarefaNaoRealizada.setUrgenteImportante(urgenteImportante.stream().filter(t -> t.isEstaRealizado() == false)
                .collect(Collectors.toList()));

        tarefaNaoRealizada.setNaoUrgenteImportante(naoUrgenteImportante.stream().filter(t -> t.isEstaRealizado() == false)
                .collect(Collectors.toList()));

        tarefaNaoRealizada.setUrgenteNaoImportante(urgenteNaoImportante.stream().filter(t -> t.isEstaRealizado() == false)
                .collect(Collectors.toList()));

        tarefaNaoRealizada.setNaoUrgenteNaoImportante(naoUrgenteNaoImportante.stream().filter(t -> t.isEstaRealizado() == false)
                .collect(Collectors.toList()));


        retorno.add(tarefaNaoRealizada);
        retorno.add(tarefaRealizada);

        return retorno;
    }
}

