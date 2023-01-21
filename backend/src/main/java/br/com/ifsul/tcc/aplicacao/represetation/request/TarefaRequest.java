package br.com.ifsul.tcc.aplicacao.represetation.request;

import br.com.ifsul.tcc.aplicacao.ENUM.CATEGORIA;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaRequest {

    @NotNull(message = "Titulo não informada")
    private String titulo;

    private String descricao;

    @NotNull(message = "Prioridade não informado")
    private CATEGORIA prioridade;

}

