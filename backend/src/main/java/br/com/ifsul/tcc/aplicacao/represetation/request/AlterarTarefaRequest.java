package br.com.ifsul.tcc.aplicacao.represetation.request;

import br.com.ifsul.tcc.aplicacao.ENUM.CATEGORIA;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlterarTarefaRequest {

    private String titulo;

    private String descricao;

    private boolean estaRealizado;

    private CATEGORIA prioridade;
    
}
