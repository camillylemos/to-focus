package br.com.ifsul.tcc.aplicacao.repository;

import br.com.ifsul.tcc.aplicacao.domain.ColecaoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColecaoUsuarioRepository extends JpaRepository<ColecaoUsuario, Integer> {
    List<ColecaoUsuario> findAllByUsuarioId(Integer usuarioId);
}
