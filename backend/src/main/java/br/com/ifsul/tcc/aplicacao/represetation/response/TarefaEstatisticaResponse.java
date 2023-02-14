package br.com.ifsul.tcc.aplicacao.represetation.response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaEstatisticaResponse {

    private Integer concluidas;

    private Map<Object, Long> porData;
}
