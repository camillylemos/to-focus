package br.com.ifsul.tcc.aplicacao.represetation.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstatisticaResponse {

    private PomodoroEstatisticaResponse pomodoro;

    private TarefaEstatisticaResponse tarefa;

    private AutenticacaoEstatisticaResponse autenticacao;
}
