package br.com.escola.status.model.repositories;

import br.com.escola.status.model.entity.StatusAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusAlunoRepository extends JpaRepository<StatusAluno, Long> {

    Optional<StatusAluno> findByIdAluno(Long id);

}
