package br.com.ifsul.tcc.aplicacao.services.tarefa;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.repository.TarefaRepository;
import br.com.ifsul.tcc.aplicacao.represetation.request.AlterarTarefaRequest;
import br.com.ifsul.tcc.aplicacao.services.usuario.UsuarioAutenticadoService;
import br.com.ifsul.tcc.aplicacao.validator.TarefaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AlterarTarefaServiceImpl implements AlterarTarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    TarefaValidator tarefaValidator;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Override
    public Tarefa alterarTarefa(AlterarTarefaRequest alterarTarefaRequest, Integer id) {
        Usuario usuario = usuarioAutenticadoService.get();

        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);

        tarefaValidator.accept(tarefa);

        tarefa.setTitulo(alterarTarefaRequest.getTitulo());
        tarefa.setDescricao(alterarTarefaRequest.getDescricao());
        tarefa.setPrioridade(alterarTarefaRequest.getPrioridade());
        tarefa.setEstaRealizado(alterarTarefaRequest.isEstaRealizado());
        tarefa.setUsuario(usuario);

        if (alterarTarefaRequest.isEstaRealizado()) {
            tarefa.setDataFinalizacao(LocalDateTime.now());
        }

        if (!alterarTarefaRequest.isEstaRealizado()) {
            tarefa.setDataFinalizacao(null);
        }

        tarefaRepository.save(tarefa);

        return tarefa;
    }
}
