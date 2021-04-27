package br.com.escola.trabalho.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_trabalho")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Trabalho implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O titulo não pode estar vazio")
    @Size(min = 3, max = 60)
    private String titulo;

    @NotNull(message = "A descrição não pode ser vazia.")
    private String descricao;

    @Column(name = "id_disciplina")
    private Long idDisciplina;

    @Column(name = "dt_fim")
    private Date dataFim;

    @Column(name = "nota_maxima")
    private Float notaMaxima;

    public void update(Long id, Trabalho trab) {
        this.id     = id;
        titulo      = trab.getTitulo();
        descricao   = trab.getDescricao();
        dataFim     = trab.getDataFim();
        notaMaxima  = trab.getNotaMaxima();
    }

}
