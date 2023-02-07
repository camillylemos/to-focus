package br.com.ifsul.tcc.aplicacao.controller;

import br.com.ifsul.tcc.aplicacao.domain.Pomodoro;
import br.com.ifsul.tcc.aplicacao.domain.PomodoroConfig;
import br.com.ifsul.tcc.aplicacao.represetation.response.MensagemResponse;
import br.com.ifsul.tcc.aplicacao.services.pomodoro.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pomodoro")
@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping("iniciar/{id}")
    public Pomodoro iniciarPomodoro(@PathVariable Integer id) {
        return iniciarPomodoroService.iniciarPomodoro(id);
    }

    @PutMapping("finalizar/{id}")
    public MensagemResponse finalizarPomodoro(@PathVariable Integer id) {
        return finalizarPomodoroService.finalizarPomodoro(id);
    }
}
