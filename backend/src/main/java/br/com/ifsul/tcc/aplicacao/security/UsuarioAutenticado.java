package br.com.ifsul.tcc.aplicacao.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UsuarioAutenticado implements UserDetails {

    @Getter
    private final String email;
    private final String nome;
    private List<String> permissoes;
    @Getter
    private final String token;

    public UsuarioAutenticado(
            String nome, String email, String token) {

        this.email = email;
        this.nome = nome;
        this.token = token;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        this.permissoes.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());

        return null;


    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
