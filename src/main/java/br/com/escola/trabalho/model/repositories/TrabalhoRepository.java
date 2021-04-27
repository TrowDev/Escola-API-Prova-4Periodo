package br.com.escola.trabalho.model.repositories;

import br.com.escola.trabalho.model.entity.Trabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {

    Optional<Trabalho> findByTitulo(String titulo);

}
