package br.com.escola.entregues.model.service.impl;

import br.com.escola.entregues.model.entity.Entregues;
import br.com.escola.entregues.model.repositories.EntreguesRepository;
import br.com.escola.entregues.model.service.EntreguesService;
import br.com.escola.exceptions.ResourceFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntreguesServiceImpl implements EntreguesService {

    @Autowired
    private EntreguesRepository entreguesRepository;

    @Override
    public Optional<Entregues> findByIdTrabalhoAndIdAluno(Long idTrabalho, Long idAluno) {
        return entreguesRepository.findByIdTrabalhoAndIdAluno(idTrabalho, idAluno);
    }

    @Override
    public Entregues save(Entregues trabalho) throws ResourceFoundException {
        Optional<Entregues> trabalhoByIds = findByIdTrabalhoAndIdAluno(trabalho.getIdTrabalho(), trabalho.getIdAluno());
        if (trabalhoByIds.isPresent()) {
            throw new ResourceFoundException("Esse Recurso Já exite na nossa base");
        }

        return entreguesRepository.save(trabalho);
    }

    @Override
    public Entregues update(Long id, Entregues titulo) {
        Optional<Entregues> trabalhoById = findById(id);
        if (trabalhoById.isPresent()) {
            var trabalhoUpdate = trabalhoById.get();
            trabalhoUpdate.update(id, titulo);
            return entreguesRepository.save(trabalhoUpdate);
        }

        return new Entregues();
    }

    @Override
    public List<Entregues> list() {
        return entreguesRepository.findAll();
    }

    @Override
    public Optional<Entregues> findById(Long id) {
        return entreguesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        entreguesRepository.deleteById(id);
    }

}
