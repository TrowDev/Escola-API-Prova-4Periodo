package br.com.escola.trabalho.model.service;

import br.com.escola.disciplina.model.entity.Aluno;
import br.com.escola.exceptions.ResourceFoundException;
import br.com.escola.service.CrudService;
import br.com.escola.trabalho.model.entity.Trabalho;

import java.util.Optional;

public interface TrabalhoService  extends CrudService<Trabalho, Long> {
    Optional<Trabalho> findByTitulo(String titulo);

    @Override
    Trabalho save(Trabalho pessoa) throws ResourceFoundException;
}
