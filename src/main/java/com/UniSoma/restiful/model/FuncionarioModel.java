package com.UniSoma.restiful.model;

import com.UniSoma.restiful.dto.DadosCadastroFuncionarioDTO;
import com.UniSoma.restiful.dto.DadosRetornoCalculoFuncionarioDTO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.Valid;

@Table(name = "funcionarios")
@Entity(name = "FuncionarioModel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;
    @Column(name = "data_nascimento", nullable = false, length = 20)
    private String dataNascimento;
    @Column(name = "telefone", nullable = false, length = 15)
    private String telefone;
    @Column(name = "endereco", nullable = false)
    private String endereco;
    @Column(name = "salario", nullable = false)
    private double salario;

    public FuncionarioModel(DadosCadastroFuncionarioDTO dados) {
        BeanUtils.copyProperties(dados,this);
    }

    public FuncionarioModel(DadosRetornoCalculoFuncionarioDTO funcionarioDTO) {
        BeanUtils.copyProperties(funcionarioDTO, this);
    }

    public void atualizar(@Valid DadosCadastroFuncionarioDTO dados) {
        BeanUtils.copyProperties(dados, this);
    }

}
