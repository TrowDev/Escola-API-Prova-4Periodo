package br.com.escola.status.model.service;

import br.com.escola.exceptions.ResourceFoundException;
import br.com.escola.service.CrudService;
import br.com.escola.status.model.entity.StatusAluno;

import java.util.Optional;

public interface StatusAlunoService extends CrudService<StatusAluno, Long> {

    @Override
    StatusAluno save(StatusAluno pessoa) throws ResourceFoundException;
}
