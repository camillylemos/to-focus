package br.com.ifsul.tcc.aplicacao.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Transactional
@Data // getters and setters
@EqualsAndHashCode(of = "email")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String senha;

    @NotEmpty
    @Email(message = "E-mail inválido")
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_nascimento", columnDefinition = "DATE")
    @NotNull
    private Date dataNascimento;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private List<Autenticacao> autenticacaos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private List<Tarefa> tarefas;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private List<PomodoroConfig> pomodoroConfigs;

    public Usuario(Integer id, String nome, String senha, String email, Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }
}
