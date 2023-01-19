package br.com.ifsul.tcc.aplicacao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Pomodoro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    private boolean estaFinalizado;

    //TODO
    //hora iniciada
    //hora finalizada

    @JoinColumn(name = "pomodoroConfig")
    @ManyToOne(targetEntity = PomodoroConfig.class, fetch = FetchType.LAZY)
    @NotNull()
    @JsonIgnore
    private PomodoroConfig pomodoroConfig;

    public Pomodoro(Integer id, boolean estaFinalizado, PomodoroConfig pomodoroConfig) {
        this.id = id;
        this.estaFinalizado = estaFinalizado;
        this.pomodoroConfig = pomodoroConfig;
    }

    public Pomodoro() {
    }
}
