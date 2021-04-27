package br.com.escola.aluno.v1;

import br.com.escola.aluno.model.service.AlunoService;
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
@RequestMapping("/v1/alunos")
public class AlunoResources {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Aluno>> list() {
        List<Aluno> pessoaList = alunoService.list();

        if (pessoaList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(pessoaList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Aluno> finById(@PathVariable("id") Long id) {
        Optional<Aluno> pessoa = alunoService.findById(id);

        if (!pessoa.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(pessoa.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Aluno> save(@Valid @RequestBody Aluno pessoa) throws ResourceFoundException {
        Aluno saved = alunoService.save(pessoa);

        if (saved == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Aluno> update(@PathVariable("id") Long id, @Valid @RequestBody Aluno pessoa) {
        Aluno updated = alunoService.update(id, pessoa);

        if (updated == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        alunoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
