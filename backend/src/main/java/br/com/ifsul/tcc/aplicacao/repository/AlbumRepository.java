package br.com.ifsul.tcc.aplicacao.repository;

import br.com.ifsul.tcc.aplicacao.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Album findByNome(String nome);
}
