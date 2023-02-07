package br.com.ifsul.tcc.aplicacao.repository;

import br.com.ifsul.tcc.aplicacao.domain.Autenticacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AutenticacaoRepository extends JpaRepository<Autenticacao, Integer> {

    @Query(value = "SELECT * FROM autenticacao a WHERE a.usuario_id = ?1 ORDER BY a.data_autenticacao DESC LIMIT 1", nativeQuery = true)
    Autenticacao findByUsuarioIdUltimoRegistro(Integer id);

    @Query(value = "SELECT COUNT(*) FROM autenticacao a WHERE a.usuario_id = ?1", nativeQuery = true)
    Long qtsAutenticacoes(Integer id);
}
