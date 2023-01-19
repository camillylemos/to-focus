package br.com.ifsul.tcc.aplicacao.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

}
