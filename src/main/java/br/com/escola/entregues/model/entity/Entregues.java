package br.com.escola.entregues.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_trabalho_entregues")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Entregues implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_trabalho")
    private Long idTrabalho;

    @Column(name = "id_aluno")
    private Long idAluno;

    @NotNull(message = "A resposta não pode ser vazia.")
    private String resposta;

    @Column(name = "dt_entrega")
    private Date dataEntrega;

    @Column(name = "nota")
    private Float nota;

    public void update(Long id, Entregues trab) {
        this.id     = id;
        resposta    = trab.getResposta();
        dataEntrega = trab.getDataEntrega();
        nota        = trab.getNota();
    }

}
