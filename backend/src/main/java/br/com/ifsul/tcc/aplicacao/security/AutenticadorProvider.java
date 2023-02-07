package br.com.ifsul.tcc.aplicacao.security;

import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class AutenticadorProvider extends AbstractUserDetailsAuthenticationProvider {

    private final String authUrl = "http://localhost:8080/usuario/me";

    @Override
    protected void additionalAuthenticationChecks(final UserDetails userDetails,
                                                  final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(
            String username,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        String token = username;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        headers.set("Authorization", token);

        HttpEntity request = new HttpEntity(headers);

        try {

            ResponseEntity<Usuario> response = new RestTemplate().exchange(
                    authUrl,
                    HttpMethod.GET,
                    request,
                    Usuario.class
            );

            Usuario userAuth = response.getBody();

            return new UsuarioAutenticado(
                    userAuth.getNome(),
                    userAuth.getEmail(),
                    token);

        } catch (Exception exception) {

            throw new UsernameNotFoundException("Usuário não econtrado");
        }
    }
}
