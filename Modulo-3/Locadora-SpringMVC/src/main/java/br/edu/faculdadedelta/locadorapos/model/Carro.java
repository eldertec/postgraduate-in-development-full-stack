package br.edu.faculdadedelta.locadorapos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A Placa é obrigatória!")
    private String placa;

    @NotBlank(message = "O Chassi é obrigatório!")
    private String chassi;

    @NotNull(message = "O Valor da diária é obrigatório!")
    @Column(precision = 10, scale = 2)
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valorDiaria;

    @ManyToOne
    @JoinColumn(name = "id_modelo")
    @NotNull(message = "O Modelo é obrigatório!")
    private Modelo modelo;
}
