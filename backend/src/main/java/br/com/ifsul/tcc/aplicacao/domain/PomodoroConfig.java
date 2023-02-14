package br.com.ifsul.tcc.aplicacao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PomodoroConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected String nomeCategoria;

    protected Integer tempoFoco;

    protected Integer tempoIntervaloCurto;

    protected Integer tempoIntervaloLongo;

    private Boolean isVisivel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public PomodoroConfig(String nomeCategoria, Integer tempoFoco, Integer tempoIntervaloCurto, Integer tempoIntervaloLongo, Boolean isVisivel, Usuario usuario) {
        this.nomeCategoria = nomeCategoria;
        this.tempoFoco = tempoFoco;
        this.tempoIntervaloCurto = tempoIntervaloCurto;
        this.tempoIntervaloLongo = tempoIntervaloLongo;
        this.isVisivel = isVisivel;
        this.usuario = usuario;
    }
}
