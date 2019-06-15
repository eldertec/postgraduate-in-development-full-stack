package br.edu.faculdadedelta.locadorapos.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Sexo {
    MASCULINO("Masculino"),FEMININO("Feminino"), NAO_BINARIO("Não Binário"), NAO_INFORMADO("Não Informar");

    private String descricao;
}
