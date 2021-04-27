package br.com.escola.aluno.model.service.impl;

import br.com.escola.aluno.model.repositories.AlunoRepository;
import br.com.escola.aluno.model.service.AlunoService;
import br.com.escola.disciplina.model.entity.Aluno;
import br.com.escola.exceptions.ResourceFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Optional<Aluno> findByEmail(String name) {
        return alunoRepository.findByEmail(name);
    }
    @Override
    public Optional<Aluno> findByMatricula(String mat) {
        return alunoRepository.findByMatricula(mat);
    }

    @Override
    public Aluno save(Aluno aluno) throws ResourceFoundException {
        Optional<Aluno> alunoById = findByEmail(aluno.getEmail());
        if (alunoById.isPresent()) {
            throw new ResourceFoundException("Esse Recurso Já exite na nossa base");
        }

        aluno.setMatricula(UUID.randomUUID().toString());

        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno update(Long id, Aluno doador) {
        Optional<Aluno> alunoById = findById(id);
        if (alunoById.isPresent()) {
            var alunoUpdate = alunoById.get();
            alunoUpdate.update(id, doador);
            return alunoRepository.save(alunoUpdate);
        }

        return new Aluno();
    }

    @Override
    public List<Aluno> list() {
        return alunoRepository.findAll();
    }

    @Override
    public Optional<Aluno> findById(Long id) {
        return alunoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        alunoRepository.deleteById(id);
    }

}
