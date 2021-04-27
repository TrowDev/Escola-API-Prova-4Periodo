package br.com.escola.disciplina.v1;

import br.com.escola.disciplina.model.entity.Disciplina;
import br.com.escola.disciplina.model.service.DisciplinaService;
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
@RequestMapping("/v1/disciplinas")
public class DiscipinaResources {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Disciplina>> list() {
        List<Disciplina> pessoaList = disciplinaService.list();

        if (pessoaList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(pessoaList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Disciplina> finById(@PathVariable("id") Long id) {
        Optional<Disciplina> pessoa = disciplinaService.findById(id);

        if (!pessoa.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(pessoa.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Disciplina> save(@Valid @RequestBody Disciplina pessoa) throws ResourceFoundException {
        Disciplina saved = disciplinaService.save(pessoa);

        if (saved == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Disciplina> update(@PathVariable("id") Long id, @Valid @RequestBody Disciplina pessoa) {
        Disciplina updated = disciplinaService.update(id, pessoa);

        if (updated == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        disciplinaService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
