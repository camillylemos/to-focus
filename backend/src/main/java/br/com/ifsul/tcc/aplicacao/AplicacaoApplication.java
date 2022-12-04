package br.com.ifsul.tcc.aplicacao;

import br.com.ifsul.tcc.aplicacao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AplicacaoApplication {

    @Autowired
    UsuarioRepository usuarioRepository;

    public static void main(String[] args) {
        SpringApplication.run(AplicacaoApplication.class, args);
    }

//	Usuario usu1 = new Usuario(null, "camilly", "sss", "camilly@gmail.com", new Date(12 - 11 - 2003));
//
//
//	usuarioRepository.save(usu1);


}
