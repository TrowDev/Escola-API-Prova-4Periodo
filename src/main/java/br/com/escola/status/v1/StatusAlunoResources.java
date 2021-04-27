package br.com.escola.status.v1;

import br.com.escola.aluno.model.service.AlunoService;
import br.com.escola.disciplina.model.entity.Aluno;
import br.com.escola.exceptions.ResourceFoundException;
import br.com.escola.status.model.entity.StatusAluno;
import br.com.escola.status.model.service.StatusAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/statusAlunos")
public class StatusAlunoResources {

    @Autowired
    private StatusAlunoService statusAlunoService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<StatusAluno>> list() {
        List<StatusAluno> statusList = statusAlunoService.list();

        if (statusList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(statusList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<StatusAluno> finById(@PathVariable("id") Long id) {
        Optional<StatusAluno> pessoa = statusAlunoService.findById(id);

        if (!pessoa.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(pessoa.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<StatusAluno> save(@Valid @RequestBody StatusAluno pessoa) throws ResourceFoundException {
        StatusAluno saved = statusAlunoService.save(pessoa);

        if (saved == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<StatusAluno> update(@PathVariable("id") Long id, @Valid @RequestBody StatusAluno pessoa) {
        StatusAluno updated = statusAlunoService.update(id, pessoa);

        if (updated == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        statusAlunoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
