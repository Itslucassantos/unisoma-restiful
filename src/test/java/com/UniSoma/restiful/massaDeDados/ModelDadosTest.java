package com.UniSoma.restiful.massaDeDados;

import com.UniSoma.restiful.model.FuncionarioModel;

public class ModelDadosTest {

    public static FuncionarioModel buildFuncionarioModel() {

        FuncionarioModel funcionarioModel = new FuncionarioModel();
        funcionarioModel.setId(1L);
        funcionarioModel.setNome("João Santos");
        funcionarioModel.setCpf("12334531002");
        funcionarioModel.setDataNascimento("12/03/1998");
        funcionarioModel.setTelefone("19944377400");
        funcionarioModel.setEndereco("Rua Batista, 233");
        funcionarioModel.setSalario(400.0);

        return funcionarioModel;
    }

}
