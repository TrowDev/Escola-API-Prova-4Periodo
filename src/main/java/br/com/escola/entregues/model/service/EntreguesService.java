package br.com.escola.entregues.model.service;

import br.com.escola.entregues.model.entity.Entregues;
import br.com.escola.exceptions.ResourceFoundException;
import br.com.escola.service.CrudService;

import java.util.Optional;

public interface EntreguesService  extends CrudService<Entregues, Long> {

    Optional<Entregues> findByIdTrabalhoAndIdAluno(Long idTrabalho, Long idAluno);

    @Override
    Entregues save(Entregues pessoa) throws ResourceFoundException;
}
