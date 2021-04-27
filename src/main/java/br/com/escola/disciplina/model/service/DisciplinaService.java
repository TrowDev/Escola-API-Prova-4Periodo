package br.com.escola.disciplina.model.service;

import br.com.escola.disciplina.model.entity.Disciplina;
import br.com.escola.exceptions.ResourceFoundException;
import br.com.escola.service.CrudService;

import java.util.Optional;

public interface DisciplinaService  extends CrudService<Disciplina, Long> {
    Optional<Disciplina> findByNome(String name);

    @Override
    Disciplina save(Disciplina pessoa) throws ResourceFoundException;
}
