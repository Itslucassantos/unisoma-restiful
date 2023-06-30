package com.UniSoma.restiful.funcionario;

import com.UniSoma.restiful.dto.DadosRetornoImpostoDeRendaDTO;
import com.UniSoma.restiful.massaDeDados.ModelDadosTest;
import com.UniSoma.restiful.model.FuncionarioModel;
import com.UniSoma.restiful.repository.FuncionarioRepository;
import com.UniSoma.restiful.service.CalcularImpostoDeRendaFuncionarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.fail;

public class CalcularImpostoDeRendaFuncionarioServiceTest {

    private static final String CPF = "12334531002";

    private CalcularImpostoDeRendaFuncionarioService impostoDeRendaFuncionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.impostoDeRendaFuncionarioService = new CalcularImpostoDeRendaFuncionarioService(funcionarioRepository);
    }

    @Test
    void naoDeveriaCalcularImpostoDeRendaQuandoFuncionarioNaoExistir() {

        Mockito.when(funcionarioRepository.getReferenceByCpf(Mockito.anyString()))
                .thenReturn(null);

        try {
            impostoDeRendaFuncionarioService.calcularImpostoDeRendaFuncionario(CPF);
            fail();
        } catch (Exception e) {
            Assertions.assertEquals(" Não é possível calcular imposto de renda, pois funcionário com esse cpf não existe! ",
                    e.getMessage());
        }
    }

    @Test
    void naoDeveriaPagarImpostoDeRendaSalarioAte2000() throws ParseException {

        FuncionarioModel funcionarioModel = ModelDadosTest.buildFuncionarioModel();

        Mockito.when(funcionarioRepository.getReferenceByCpf(funcionarioModel.getCpf()))
                .thenReturn(funcionarioModel);

        DadosRetornoImpostoDeRendaDTO impostoDeRendaDTO = impostoDeRendaFuncionarioService
                .calcularImpostoDeRendaFuncionario(funcionarioModel.getCpf());

        Assertions.assertEquals(" Isento ", impostoDeRendaDTO.getImposto());
    }

    @Test
    void deveriaPagar8PorCentoDeImpostoDeRendaSalarioDe2001Ate3000() throws ParseException {

        FuncionarioModel funcionarioModel = ModelDadosTest.buildFuncionarioModel();
        funcionarioModel.setSalario(2500.0);

        Mockito.when(funcionarioRepository.getReferenceByCpf(funcionarioModel.getCpf()))
                .thenReturn(funcionarioModel);

        DadosRetornoImpostoDeRendaDTO impostoDeRendaDTO = impostoDeRendaFuncionarioService
                .calcularImpostoDeRendaFuncionario(funcionarioModel.getCpf());

        Assertions.assertEquals("R$ " + 200.0, impostoDeRendaDTO.getImposto());
    }

    @Test
    void deveriaPagar18PorCentoDeImpostoDeRendaSalarioDe3001Ate4500() throws ParseException {

        FuncionarioModel funcionarioModel = ModelDadosTest.buildFuncionarioModel();
        funcionarioModel.setSalario(3500.0);

        Mockito.when(funcionarioRepository.getReferenceByCpf(funcionarioModel.getCpf()))
                .thenReturn(funcionarioModel);

        DadosRetornoImpostoDeRendaDTO impostoDeRendaDTO = impostoDeRendaFuncionarioService
                .calcularImpostoDeRendaFuncionario(funcionarioModel.getCpf());

        Assertions.assertEquals("R$ " + 630.0, impostoDeRendaDTO.getImposto());
    }

    @Test
    void deveriaPagar28PorCentoDeImpostoDeRendaSalarioAcimaDe4500() throws ParseException {

        FuncionarioModel funcionarioModel = ModelDadosTest.buildFuncionarioModel();
        funcionarioModel.setSalario(4800.0);

        Mockito.when(funcionarioRepository.getReferenceByCpf(funcionarioModel.getCpf()))
                .thenReturn(funcionarioModel);

        DadosRetornoImpostoDeRendaDTO impostoDeRendaDTO = impostoDeRendaFuncionarioService
                .calcularImpostoDeRendaFuncionario(funcionarioModel.getCpf());

        Assertions.assertEquals("R$ " + 1344.0, impostoDeRendaDTO.getImposto());
    }

}
