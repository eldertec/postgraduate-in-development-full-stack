package br.edu.faculdadedelta.locadorapos.model;

import br.edu.faculdadedelta.locadorapos.model.type.Sexo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Nome é obrigatório!")
    private String nome;

    @CPF
    @Column(nullable = false, unique = true)
    @NotBlank(message = "O CPF é obrigatório!")
    private String cpf;

    @NotBlank(message = "O CNH é obrigatório!")
    private String cnh;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Sexo é obrigatório!")
    private Sexo sexo;
}
