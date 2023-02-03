package br.com.ifsul.tcc.aplicacao.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
@Entity
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

    private String token;

    public Usuario(String nome, String senha, String email, Date dataNascimento) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "usuario_id")
//    private List<Autenticacao> autenticacaos;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "usuario_id")
    private List<Tarefa> tarefas;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "usuario_id")
    private List<PomodoroConfig> pomodoroConfigs;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "usuario_id")
    private List<Pomodoro> pomodoro;


}
