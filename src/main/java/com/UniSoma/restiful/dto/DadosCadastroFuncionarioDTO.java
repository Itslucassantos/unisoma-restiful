package com.UniSoma.restiful.dto;

import com.UniSoma.restiful.model.FuncionarioModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosCadastroFuncionarioDTO {

    private Long id;
    @NotNull(message = "Nome não pode ser null")
    private String nome;
    @NotNull(message = "Cpf não pode ser null")
    private String cpf;
    @NotNull(message = "Data de Nascimento não pode ser null")
    private String dataNascimento;
    @NotNull(message = "Telefone não pode ser null")
    private String telefone;
    @NotNull(message = "Endereço não pode ser null")
    private String endereco;
    @NotNull(message = "Salário não pode ser null")
    private double salario;

    public DadosCadastroFuncionarioDTO(FuncionarioModel funcionarioModel) {
        BeanUtils.copyProperties(funcionarioModel, this);
    }

}
