package br.com.ifsul.tcc.aplicacao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Autenticacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dataAutenticacao;

    private LocalTime horarioAutenticacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Autenticacao(LocalDate dataAutenticacao, LocalTime horarioAutenticacao, Usuario usuario) {
        this.dataAutenticacao = dataAutenticacao;
        this.horarioAutenticacao = horarioAutenticacao;
        this.usuario = usuario;
    }
}
