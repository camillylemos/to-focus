package br.com.ifsul.tcc.aplicacao.repository;

import br.com.ifsul.tcc.aplicacao.domain.Figura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiguraRepository extends JpaRepository<Figura, Integer> {
    Figura findByNomeAndAlbumId(String nome, Integer album_id);
}
