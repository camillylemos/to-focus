package br.com.ifsul.tcc.aplicacao.repository;

import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT * FROM usuario u WHERE u.email = ?1 and u.senha = ?2", nativeQuery = true)
    Usuario login(String email, String senha);

    Usuario findByToken(String token);

    Usuario findByEmail(String email);
}
