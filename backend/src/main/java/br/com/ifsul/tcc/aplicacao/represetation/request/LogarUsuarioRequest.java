package br.com.ifsul.tcc.aplicacao.represetation.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogarUsuarioRequest {

    @NotEmpty
    @Email(message = "E-mail inválido")
    private String email;

    @NotNull(message = "Senha incorreta")
    private String senha;

}
