package br.com.ifsul.tcc.aplicacao.controller;

import br.com.ifsul.tcc.aplicacao.domain.Pomodoro;
import br.com.ifsul.tcc.aplicacao.domain.PomodoroConfig;
import br.com.ifsul.tcc.aplicacao.services.pomodoro.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(allowedHeaders = {"Authorization", "Origin"})
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
    List<PomodoroConfig> buscarConfiguracaoPomodoro(@RequestHeader String authorization) {
        return buscarConfiguracaoPomodoroService.buscarConfiguracaoPomodoro(authorization);
    }

    @PostMapping()
    public PomodoroConfig criarPomodoroConfig(@RequestBody @Valid PomodoroConfig request, @RequestHeader String authorization) {
        return criarPomodoroConfigService.criarPomodoroConfig(request, authorization);
    }

    @DeleteMapping("/{id}")
    public void deletarPomodoroConfig(@PathVariable Integer id) {
        deletarPomodoroConfigService.deletarPomodoroConfig(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("iniciar/{id}")
    public Pomodoro iniciarPomodoro(@PathVariable Integer id, @RequestHeader String authorization) {
        return iniciarPomodoroService.iniciarPomodoro(id, authorization);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("finalizar/{id}")
    public void finalizarPomodoro(@PathVariable Integer id) {
        finalizarPomodoroService.finalizarPomodoro(id);
    }
}
