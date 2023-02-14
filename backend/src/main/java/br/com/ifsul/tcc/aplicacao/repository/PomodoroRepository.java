package br.com.ifsul.tcc.aplicacao.repository;

import br.com.ifsul.tcc.aplicacao.domain.Pomodoro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PomodoroRepository extends JpaRepository<Pomodoro, Integer> {

    Pomodoro findByIdAndUsuarioId(Integer id, Integer usuarioId);

    @Query(value = "SELECT COUNT(*) FROM pomodoro p WHERE p.usuario_id = ?1 AND p.esta_finalizado = ?2", nativeQuery = true)
    Long qntsPomododorosFinalizados(Integer usuarioId, boolean estaFinalizado);

    @Query(value = "SELECT * FROM pomodoro p WHERE p.usuario_id = ?1 AND p.esta_finalizado = ?2", nativeQuery = true)
    List<Pomodoro> findByIdUsuarioAndEstaFinalizado(Integer usuarioId, boolean estaRealizado);
}
