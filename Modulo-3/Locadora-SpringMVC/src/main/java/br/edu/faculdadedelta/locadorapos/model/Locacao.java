package br.edu.faculdadedelta.locadorapos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Locacao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 10, scale = 2)
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valorTotal;

    @NotNull(message = "A Data de Locação é obrigatória!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataLocacao;

    @NotNull(message = "A Data de Devolução é obrigatória!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "id_carro")
    @NotNull(message = "O Carro é obrigatório!")
    private Carro carro;

    @ManyToOne
    @JoinColumn(name = "id_motorista")
    @NotNull(message = "O Motorista é obrigatório!")
    private Motorista motorista;
}
