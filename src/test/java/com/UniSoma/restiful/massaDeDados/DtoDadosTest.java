package com.UniSoma.restiful.massaDeDados;

import com.UniSoma.restiful.dto.DadosCadastroFuncionarioDTO;

public class DtoDadosTest {

    public static DadosCadastroFuncionarioDTO buildDadosCadastroFuncionarioDTO() {

        DadosCadastroFuncionarioDTO dadosCadastroFuncionarioDTO = new DadosCadastroFuncionarioDTO();
        dadosCadastroFuncionarioDTO.setNome("João Santos");
        dadosCadastroFuncionarioDTO.setCpf("12334531002");
        dadosCadastroFuncionarioDTO.setDataNascimento("12/03/1998");
        dadosCadastroFuncionarioDTO.setTelefone("19944377400");
        dadosCadastroFuncionarioDTO.setEndereco("Rua Batista, 233");
        dadosCadastroFuncionarioDTO.setSalario(400.0);

        return dadosCadastroFuncionarioDTO;
    }


}
