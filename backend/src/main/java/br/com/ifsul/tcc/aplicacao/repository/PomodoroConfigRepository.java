package br.com.ifsul.tcc.aplicacao.repository;

import br.com.ifsul.tcc.aplicacao.domain.PomodoroConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PomodoroConfigRepository extends JpaRepository<PomodoroConfig, Integer> {

    Optional<PomodoroConfig> findByIdAndUsuarioId(Integer id, Integer usuarioId);

    List<PomodoroConfig> findByUsuarioId(Integer id);
}
