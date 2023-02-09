package br.com.ifsul.tcc.aplicacao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "album_id")
    private List<Figura> figuras;
}
