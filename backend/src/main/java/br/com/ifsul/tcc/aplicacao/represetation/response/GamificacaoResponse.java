package br.com.ifsul.tcc.aplicacao.represetation.response;

import br.com.ifsul.tcc.aplicacao.domain.ColecaoUsuario;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GamificacaoResponse {

    private String mensagem;

    private List<ColecaoUsuario> colecao;
}
