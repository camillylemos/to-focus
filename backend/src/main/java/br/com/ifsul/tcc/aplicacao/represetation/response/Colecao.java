package br.com.ifsul.tcc.aplicacao.represetation.response;

import br.com.ifsul.tcc.aplicacao.domain.Figura;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Colecao {

    private String album;

    private List<Figura> figuras;
}
