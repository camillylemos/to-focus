package br.com.ifsul.tcc.aplicacao.security;


//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
//public class SegurancaConfig {
//
//    @Autowired
//    private AutenticadorProvider provider;
//
//    @Bean
//    public void configure(WebSecurity webSecurity) {
//        webSecurity
//                .ignoring()
//                .antMatchers("/**");
//    }
//
//    private static final RequestMatcher URLS_PROTEGIDAS = new OrRequestMatcher(
//            new AntPathRequestMatcher("/pomodoro/**"),
//            new AntPathRequestMatcher("/tarefa/**"),
//            new AntPathRequestMatcher("/usuario/me")
//    );
//
//    @Bean
//    protected DefaultSecurityFilterChain configure(HttpSecurity http) throws Exception {
//
////        http
////                .csrf().disable()
////                .cors().and()
////                .authorizeRequests()
////                .antMatchers("/usuario/login", "/usuario/cadastrar")
////                .permitAll().and()
////                .authorizeRequests()
////                .antMatchers("/pomodoro/**").authenticated()
////                .antMatchers("/tarefa/**").authenticated()
////                .anyRequest().permitAll()
////                .and().httpBasic();
////
////        return http.build();
//
////        return http
////                .authorizeRequests()
////                .antMatchers("/tarefa/**", "/pomodoro/**").authenticated()
////                .anyRequest().permitAll()
////                .and()
////                .csrf().disable()
////                .build();
//
//
//        return http
//                .exceptionHandling()
//                .and()
//                .authenticationProvider(provider)
//                .addFilterBefore(tokenFiltro(), AnonymousAuthenticationFilter.class)
//                .authorizeRequests()
//                .requestMatchers(URLS_PROTEGIDAS)
//                .authenticated()
//                .and()
//                .csrf().disable()
//                .build();
//        ;
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://yourdomain.com"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
//        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
//        configuration.setAllowCredentials(true);
//        configuration.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//
//    @Bean
//    AutenticacaoFilter tokenFiltro() throws Exception {
//        final AutenticacaoFilter filter = new AutenticacaoFilter(URLS_PROTEGIDAS);
//        filter.setAuthenticationManager(authenticationManager());
//        return filter;
//    }
//}
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//public class SegurancaConfig {
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .cors().and()
//                .authorizeRequests().antMatchers("/login/**", "/usuario/**").permitAll().and()
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .httpBasic();
//
//        return http.build();
//    }
//}
