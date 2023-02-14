package br.com.ifsul.tcc.aplicacao.represetation.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PomodoroEstatisticaResponse {

    private Long finalizados;

    private Long naoFinalizados;

    private Long tempoTotalFoco;
}
