package br.com.escola.status.model.service.impl;

import br.com.escola.exceptions.ResourceFoundException;
import br.com.escola.status.model.entity.StatusAluno;
import br.com.escola.status.model.repositories.StatusAlunoRepository;
import br.com.escola.status.model.service.StatusAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusAlunoServiceImpl implements StatusAlunoService {

    @Autowired
    private StatusAlunoRepository statusAlunoRepository;

    @Override
    public StatusAluno save(StatusAluno aluno) throws ResourceFoundException {
        Optional<StatusAluno> statusAlunoById = findById(aluno.getIdAluno());
        if (statusAlunoById.isPresent()) {
            throw new ResourceFoundException("Esse Recurso Já exite na nossa base");
        }

        return statusAlunoRepository.save(aluno);
    }

    @Override
    public StatusAluno update(Long id, StatusAluno doador) {
        Optional<StatusAluno> alunoById = findById(id);
        if (alunoById.isPresent()) {
            var alunoUpdate = alunoById.get();
            alunoUpdate.update(id, doador);
            return statusAlunoRepository.save(alunoUpdate);
        }

        return new StatusAluno();
    }

    @Override
    public List<StatusAluno> list() {
        return statusAlunoRepository.findAll();
    }

    @Override
    public Optional<StatusAluno> findById(Long id) {
        return statusAlunoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        statusAlunoRepository.deleteById(id);
    }

}
