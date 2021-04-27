package br.com.escola.entregues.model.repositories;

import br.com.escola.entregues.model.entity.Entregues;
import br.com.escola.trabalho.model.entity.Trabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntreguesRepository extends JpaRepository<Entregues, Long> {
    Optional<Entregues> findByIdTrabalhoAndIdAluno(Long idTrabalho, Long idAluno);
}
