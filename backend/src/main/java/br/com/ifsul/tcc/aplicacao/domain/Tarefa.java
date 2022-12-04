package br.com.ifsul.tcc.aplicacao.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Data
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String titulo;

    private String descricao;

    @NotEmpty
    private boolean estaRealizado;

    @NotEmpty
    private LocalDateTime dataCriacao;

    @NotEmpty
    private LocalDateTime dataFinalizacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
}
