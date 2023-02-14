package br.com.ifsul.tcc.aplicacao.services.estatistica;

import br.com.ifsul.tcc.aplicacao.domain.Pomodoro;
import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.repository.AutenticacaoRepository;
import br.com.ifsul.tcc.aplicacao.repository.PomodoroRepository;
import br.com.ifsul.tcc.aplicacao.repository.TarefaRepository;
import br.com.ifsul.tcc.aplicacao.represetation.response.AutenticacaoEstatisticaResponse;
import br.com.ifsul.tcc.aplicacao.represetation.response.EstatisticaResponse;
import br.com.ifsul.tcc.aplicacao.represetation.response.PomodoroEstatisticaResponse;
import br.com.ifsul.tcc.aplicacao.represetation.response.TarefaEstatisticaResponse;
import br.com.ifsul.tcc.aplicacao.services.usuario.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuscarEstatisticaServiceImpl implements BuscarEstatisticaService {

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    PomodoroRepository pomodoroRepository;

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    AutenticacaoRepository autenticacaoRepository;

    @Override
    public EstatisticaResponse buscarEstatistica() {

        Usuario usuario = usuarioAutenticadoService.get();

        Long tempoTotalFoco = Long.valueOf(0);

        Long pomodorosFinalizados = pomodoroRepository.qntsPomododorosFinalizados(usuario.getId(), true);

        Long pomodorosNaoFinalizados = pomodoroRepository.qntsPomododorosFinalizados(usuario.getId(), false);

        List<Pomodoro> listaPomodoroFinalizado = pomodoroRepository.findByIdUsuarioAndEstaFinalizado(usuario.getId(), true);

        for (Pomodoro p : listaPomodoroFinalizado) {
            int tempoFoco = p.getPomodoroConfig().getTempoFoco();
            tempoTotalFoco += tempoFoco * 4;
        }

        List<Tarefa> listaTarefasConcluidas = tarefaRepository.findByUsuarioIdAndEstaRealizado(usuario.getId(), true);

        Integer tarefasConcluidas = listaTarefasConcluidas.size();

        Map<Object, Long> tarefasAgrupadasPorData = listaTarefasConcluidas.stream()
                .collect(Collectors.groupingBy(tarefa -> tarefa.getDataFinalizacao().toLocalDate(), Collectors.counting()));

        Long diasAutenticados = autenticacaoRepository.qtsAutenticacoes(usuario.getId());

        EstatisticaResponse relatório = new EstatisticaResponse(
                new PomodoroEstatisticaResponse(pomodorosFinalizados, pomodorosNaoFinalizados, tempoTotalFoco),
                new TarefaEstatisticaResponse(tarefasConcluidas, tarefasAgrupadasPorData),
                new AutenticacaoEstatisticaResponse(diasAutenticados));

        return relatório;
    }
}
