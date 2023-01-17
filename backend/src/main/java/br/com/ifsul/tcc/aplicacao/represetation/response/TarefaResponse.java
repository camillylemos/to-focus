package br.com.ifsul.tcc.aplicacao.represetation.response;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaResponse {

    List<Tarefa> urgenteImportante;

    List<Tarefa> naoUrgenteImportante;

    List<Tarefa> urgenteNaoImportante;

    List<Tarefa> naoUrgenteNaoImportante;
}
