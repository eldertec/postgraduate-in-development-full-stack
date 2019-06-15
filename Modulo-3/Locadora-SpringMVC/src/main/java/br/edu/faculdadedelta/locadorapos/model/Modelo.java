package br.edu.faculdadedelta.locadorapos.model;

import br.edu.faculdadedelta.locadorapos.model.type.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo Descrição é obrigatório!")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    @NotNull(message = "O Fabricante é obrigatório!")
    private Fabricante fabricante;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "A Categoria é obrigatória!")
    private Categoria categoria;
}
