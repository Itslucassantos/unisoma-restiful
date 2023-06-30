package com.UniSoma.restiful.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@Getter
@NoArgsConstructor
public class DadosRetornoCalculoFuncionarioDTO {

    private String cpf;
    private double novoSalario;
    private double reajusteGanho;
    private String percentual;
    public DadosRetornoCalculoFuncionarioDTO(String cpf, double novoSalario, double reajusteGanho, String percentual) {
        this.cpf = cpf;
        this.novoSalario = novoSalario;
        this.reajusteGanho = reajusteGanho;
        this.percentual = percentual;
    }

}
