package br.com.escola.trabalho.model.service.impl;

import br.com.escola.exceptions.ResourceFoundException;
import br.com.escola.trabalho.model.entity.Trabalho;
import br.com.escola.trabalho.model.repositories.TrabalhoRepository;
import br.com.escola.trabalho.model.service.TrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrabalhoServiceImpl implements TrabalhoService {

    @Autowired
    private TrabalhoRepository trabalhoRepository;

    @Override
    public Optional<Trabalho> findByTitulo(String titulo) {
        return trabalhoRepository.findByTitulo(titulo);
    }

    @Override
    public Trabalho save(Trabalho trabalho) throws ResourceFoundException {
        Optional<Trabalho> trabalhoById = findByTitulo(trabalho.getTitulo());
        if (trabalhoById.isPresent()) {
            throw new ResourceFoundException("Esse Recurso Já exite na nossa base");
        }

        return trabalhoRepository.save(trabalho);
    }

    @Override
    public Trabalho update(Long id, Trabalho titulo) {
        Optional<Trabalho> trabalhoById = findById(id);
        if (trabalhoById.isPresent()) {
            var trabalhoUpdate = trabalhoById.get();
            trabalhoUpdate.update(id, titulo);
            return trabalhoRepository.save(trabalhoUpdate);
        }

        return new Trabalho();
    }

    @Override
    public List<Trabalho> list() {
        return trabalhoRepository.findAll();
    }

    @Override
    public Optional<Trabalho> findById(Long id) {
        return trabalhoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        trabalhoRepository.deleteById(id);
    }

}
