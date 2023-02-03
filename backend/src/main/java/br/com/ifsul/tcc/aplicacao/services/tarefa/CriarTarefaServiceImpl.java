package br.com.ifsul.tcc.aplicacao.services.tarefa;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.mapper.TarefaMapper;
import br.com.ifsul.tcc.aplicacao.repository.TarefaRepository;
import br.com.ifsul.tcc.aplicacao.represetation.request.TarefaRequest;
import br.com.ifsul.tcc.aplicacao.services.usuario.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriarTarefaServiceImpl implements CriarTarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    TarefaMapper tarefaMapper;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Override
    public void criarTarefa(TarefaRequest request, String token) {
        Usuario usuario = usuarioAutenticadoService.get(token);

        Tarefa tarefa = tarefaMapper.toDomain(request, usuario);

        tarefaRepository.save(tarefa);
    }

}
