package br.com.ifsul.tcc.aplicacao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @JsonIgnore
    private Usuario usuario;


    public Pomodoro(boolean estaFinalizado, PomodoroConfig pomodoroConfig, Usuario usuario) {
        this.estaFinalizado = estaFinalizado;
        this.pomodoroConfig = pomodoroConfig;
        this.usuario = usuario;
    }
}
