package br.com.escola.status.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_status_aluno")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatusAluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_disciplina")
    private Long idDisciplina;

    @Column(name = "id_aluno")
    private Long idAluno;

    private Integer faltas;

    private Float frequencia; 

    public void update(Long id, StatusAluno pessoa) {
        this.id = id;
        faltas = pessoa.getFaltas();
        frequencia = pessoa.getFrequencia();
    }

}
