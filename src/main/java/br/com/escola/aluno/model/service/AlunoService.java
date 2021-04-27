package br.com.escola.aluno.model.service;

import br.com.escola.disciplina.model.entity.Aluno;
import br.com.escola.exceptions.ResourceFoundException;
import br.com.escola.service.CrudService;

import java.util.Optional;

public interface AlunoService  extends CrudService<Aluno, Long> {
    Optional<Aluno> findByEmail(String email);
    Optional<Aluno> findByMatricula(String mat);

    @Override
    Aluno save(Aluno pessoa) throws ResourceFoundException;
}
