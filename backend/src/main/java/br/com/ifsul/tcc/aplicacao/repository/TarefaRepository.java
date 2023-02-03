package br.com.ifsul.tcc.aplicacao.repository;

import br.com.ifsul.tcc.aplicacao.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
    @Query(value = "SELECT * FROM tarefa t WHERE t.usuario_id = ?1 ORDER BY t.prioridade, t.data_criacao", nativeQuery = true)
    List<Tarefa> buscarTarefasOrdenadasPorPrioridade(Integer id);
}
