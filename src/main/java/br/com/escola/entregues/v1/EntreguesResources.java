package br.com.escola.entregues.v1;

import br.com.escola.entregues.model.entity.Entregues;
import br.com.escola.entregues.model.service.EntreguesService;
import br.com.escola.exceptions.ResourceFoundException;
import br.com.escola.trabalho.model.entity.Trabalho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/entregues")
public class EntreguesResources {

    @Autowired
    private EntreguesService entregueService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Entregues>> list() {
        List<Entregues> trabalhoList = entregueService.list();

        if (trabalhoList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(trabalhoList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Entregues> finById(@PathVariable("id") Long id) {
        Optional<Entregues> entregues = entregueService.findById(id);

        if (!entregues.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(entregues.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Entregues> save(@Valid @RequestBody Entregues trabalho) throws ResourceFoundException {
        Entregues saved = entregueService.save(trabalho);

        if (saved == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Entregues> update(@PathVariable("id") Long id, @Valid @RequestBody Entregues trabalho) {
        Entregues updated = entregueService.update(id, trabalho);

        if (updated == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        entregueService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
