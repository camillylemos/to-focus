package br.com.ifsul.tcc.aplicacao.controller;

import br.com.ifsul.tcc.aplicacao.domain.PomodoroConfig;
import br.com.ifsul.tcc.aplicacao.services.pomodoro.BuscarConfiguracaoPomodoroService;
import br.com.ifsul.tcc.aplicacao.services.pomodoro.FinalizarPomodoroService;
import br.com.ifsul.tcc.aplicacao.services.pomodoro.IniciarPomodoroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pomodoro")
public class PomodoroController {

    @Autowired
    BuscarConfiguracaoPomodoroService buscarConfiguracaoPomodoroService;
    @Autowired
    IniciarPomodoroService iniciarPomodoroService;

    @Autowired
    FinalizarPomodoroService finalizarPomodoroService;

    @GetMapping()
    List<PomodoroConfig> buscarConfiguracaoPomodoro() {
        return buscarConfiguracaoPomodoroService.buscarConfiguracaoPomodoro();
    }

    @PostMapping("/{id}")
    void iniciarPomodoro(@PathVariable Integer id) {
        iniciarPomodoroService.iniciarPomodoro(id);
    }

    @PostMapping("finalizar/{id}")
    void finalizarPomodoro(@PathVariable Integer id) {
        finalizarPomodoroService.finalizarPomodoro(id);
    }
}
