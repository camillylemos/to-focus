package br.com.ifsul.tcc.aplicacao.represetation.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefasAgrupadasPorData {

    private LocalDate data;

    private long tarefasFinalizadas;

}
