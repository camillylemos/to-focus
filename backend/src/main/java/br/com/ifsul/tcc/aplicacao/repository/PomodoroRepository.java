package br.com.ifsul.tcc.aplicacao.repository;

import br.com.ifsul.tcc.aplicacao.domain.Pomodoro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PomodoroRepository extends JpaRepository<Pomodoro, Integer> {

}
