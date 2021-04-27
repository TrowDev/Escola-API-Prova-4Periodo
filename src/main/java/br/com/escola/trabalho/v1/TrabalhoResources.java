package br.com.escola.trabalho.v1;

import br.com.escola.trabalho.model.entity.Trabalho;
import br.com.escola.trabalho.model.service.TrabalhoService;
import br.com.escola.disciplina.model.entity.Aluno;
import br.com.escola.exceptions.ResourceFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trabalhos")
public class TrabalhoResources {

    @Autowired
    private TrabalhoService trabalhoService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Trabalho>> list() {
        List<Trabalho> trabalhoList = trabalhoService.list();

        if (trabalhoList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(trabalhoList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Trabalho> finById(@PathVariable("id") Long id) {
        Optional<Trabalho> trabalho = trabalhoService.findById(id);

        if (!trabalho.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(trabalho.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Trabalho> save(@Valid @RequestBody Trabalho trabalho) throws ResourceFoundException {
        Trabalho saved = trabalhoService.save(trabalho);

        if (saved == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Trabalho> update(@PathVariable("id") Long id, @Valid @RequestBody Trabalho trabalho) {
        Trabalho updated = trabalhoService.update(id, trabalho);

        if (updated == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        trabalhoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
