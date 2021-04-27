package br.com.escola.disciplina.model.service.impl;

import br.com.escola.disciplina.model.entity.Disciplina;
import br.com.escola.disciplina.model.repositories.DisciplinaRepository;
import br.com.escola.disciplina.model.service.DisciplinaService;
import br.com.escola.exceptions.ResourceFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Override
    public Optional<Disciplina> findByNome(String name) {
        return disciplinaRepository.findByNome(name);
    }

    @Override
    public Disciplina save(Disciplina disciplina) throws ResourceFoundException {
        Optional<Disciplina> disciplinaById = findByNome(disciplina.getNome());
        if (disciplinaById.isPresent()) {
            throw new ResourceFoundException("Esse Recurso Já exite na nossa base");
        }

        return disciplinaRepository.save(disciplina);
    }

    @Override
    public Disciplina update(Long id, Disciplina doador) {
        Optional<Disciplina> disciplinaById = findById(id);
        if (disciplinaById.isPresent()) {
            var disciplinaUpdate = disciplinaById.get();
            disciplinaUpdate.update(id, doador);
            return disciplinaRepository.save(disciplinaUpdate);
        }

        return new Disciplina();
    }

    @Override
    public List<Disciplina> list() {
        return disciplinaRepository.findAll();
    }

    @Override
    public Optional<Disciplina> findById(Long id) {
        return disciplinaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        disciplinaRepository.deleteById(id);
    }

}
