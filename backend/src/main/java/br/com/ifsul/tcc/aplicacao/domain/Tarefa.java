package br.com.ifsul.tcc.aplicacao.domain;

import br.com.ifsul.tcc.aplicacao.ENUM.CATEGORIA;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String titulo;

    private String descricao;

    private boolean estaRealizado;


    private LocalDateTime dataCriacao;

    private LocalDateTime dataFinalizacao;

    private CATEGORIA prioridade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Tarefa(String titulo, String descricao, boolean estaRealizado, LocalDateTime dataCriacao, LocalDateTime dataFinalizacao, CATEGORIA prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.estaRealizado = estaRealizado;
        this.dataCriacao = dataCriacao;
        this.dataFinalizacao = dataFinalizacao;
        this.prioridade = prioridade;
    }
}
