package br.com.escola.disciplina.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tb_aluno")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 60)
    private String nome;

    @NotNull(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 60)
    private String email;

    private String matricula;

    public void update(Long id, Aluno pessoa) {
        this.id = id;
        this.nome = pessoa.getNome();
        this.email = pessoa.getEmail();
    }

}
