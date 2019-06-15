package br.edu.faculdadedelta.locadorapos.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Categoria {
    HATCH("Hatch"), SEDAN("Sedan"), UTILITARIO("Utilitário"), ESPORTIVO("Esportivo");

    private String descricao;
}
