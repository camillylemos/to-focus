package br.com.ifsul.tcc.aplicacao.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PomodoroConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected String nomeCategoria;

    protected Integer tempoFoco;

    protected Integer tempoIntervaloCurto;

    protected Integer tempoIntervaloLongo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    protected Usuario usuario;
}
