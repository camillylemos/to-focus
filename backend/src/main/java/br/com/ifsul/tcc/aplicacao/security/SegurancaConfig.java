package br.com.ifsul.tcc.aplicacao.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SegurancaConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticadorProvider provider;

//    @Override
//    public void configure(WebSecurity webSecurity) {
//        webSecurity
//                .ignoring()
//                .antMatchers("/**");
//    }

    private static final RequestMatcher URLS_PROTEGIDAS = new OrRequestMatcher(
            new AntPathRequestMatcher("/pomodoro/**"),
            new AntPathRequestMatcher("/tarefa/**"),
            new AntPathRequestMatcher("/autenticacao/**"),
            new AntPathRequestMatcher("/estatistica/**"),
            new AntPathRequestMatcher("/colecao/**")
    );

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token", "authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                .exceptionHandling()
                .and()
                .authenticationProvider(provider)
                .addFilterBefore(tokenFiltro(), AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                .requestMatchers(URLS_PROTEGIDAS)
                .authenticated()
                .and()
                .cors().configurationSource(corsConfigurationSource()).and()
                .csrf().disable();
    }

    @Bean
    AutenticacaoFilter tokenFiltro() throws Exception {
        final AutenticacaoFilter filter = new AutenticacaoFilter(URLS_PROTEGIDAS);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }
}
