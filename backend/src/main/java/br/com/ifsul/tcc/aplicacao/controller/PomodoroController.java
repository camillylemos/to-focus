package br.com.ifsul.tcc.aplicacao.controller;

import br.com.ifsul.tcc.aplicacao.domain.Pomodoro;
import br.com.ifsul.tcc.aplicacao.domain.PomodoroConfig;
import br.com.ifsul.tcc.aplicacao.services.pomodoro.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired
    CriarPomodoroConfigService criarPomodoroConfigService;

    @Autowired
    DeletarPomodoroConfigService deletarPomodoroConfigService;

    @GetMapping()
    List<PomodoroConfig> buscarConfiguracaoPomodoro() {
        return buscarConfiguracaoPomodoroService.buscarConfiguracaoPomodoro();
    }

    @PostMapping()
    public PomodoroConfig criarPomodoroConfig(@RequestBody @Valid PomodoroConfig request) {
        return criarPomodoroConfigService.criarPomodoroConfig(request);
    }

    @DeleteMapping("/{id}")
    public void deletarPomodoroConfig(@PathVariable Integer id) {
        deletarPomodoroConfigService.deletarPomodoroConfig(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("iniciar/{id}")
    public Pomodoro iniciarPomodoro(@PathVariable Integer id) {
        return iniciarPomodoroService.iniciarPomodoro(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("finalizar/{id}")
    public Pomodoro finalizarPomodoro(@PathVariable Integer id) {
        return finalizarPomodoroService.finalizarPomodoro(id);
    }
}
