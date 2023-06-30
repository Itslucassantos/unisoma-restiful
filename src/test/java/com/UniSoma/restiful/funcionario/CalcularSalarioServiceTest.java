package com.UniSoma.restiful.funcionario;

import com.UniSoma.restiful.dto.DadosRetornoCalculoFuncionarioDTO;
import com.UniSoma.restiful.massaDeDados.ModelDadosTest;
import com.UniSoma.restiful.model.FuncionarioModel;
import com.UniSoma.restiful.repository.FuncionarioRepository;
import com.UniSoma.restiful.service.CalcularSalarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.fail;

public class CalcularSalarioServiceTest {

    private static final String CPF = "12334531002";

    private CalcularSalarioService calcularSalarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.calcularSalarioService = new CalcularSalarioService(funcionarioRepository);
    }

    @Test
    void naoDeveriaCalcularSalarioQuandoFuncionarioNaoExistir() {

        Mockito.when(funcionarioRepository.getReferenceByCpf(Mockito.anyString()))
                .thenReturn(null);

        try {
            calcularSalarioService.calcularSalarioFuncionario(CPF);
            fail();
        } catch (Exception e) {
            Assertions.assertEquals(" Funcionário com esse cpf não existe! ",
                    e.getMessage());
        }
    }

    @Test
    void deveriaAumentar15PorCentoDoSalarioAte400() throws ParseException {

        FuncionarioModel funcionarioModel = ModelDadosTest.buildFuncionarioModel();

        Mockito.when(funcionarioRepository.getReferenceByCpf(funcionarioModel.getCpf()))
                .thenReturn(funcionarioModel);

        Mockito.when(funcionarioRepository.save(funcionarioModel)).thenReturn(funcionarioModel);

        DadosRetornoCalculoFuncionarioDTO salarioAtualizado = calcularSalarioService
                .calcularSalarioFuncionario(funcionarioModel.getCpf());

        Assertions.assertEquals(460.0, salarioAtualizado.getNovoSalario());
        Assertions.assertEquals(60.0, salarioAtualizado.getReajusteGanho());
        Assertions.assertEquals("15%", salarioAtualizado.getPercentual());
    }

    @Test
    void deveriaAumentar12PorCentoDoSalarioDe401Ate800() throws ParseException {

        FuncionarioModel funcionarioModel = ModelDadosTest.buildFuncionarioModel();
        funcionarioModel.setSalario(600.0);

        Mockito.when(funcionarioRepository.getReferenceByCpf(funcionarioModel.getCpf()))
                .thenReturn(funcionarioModel);

        Mockito.when(funcionarioRepository.save(funcionarioModel)).thenReturn(funcionarioModel);

        DadosRetornoCalculoFuncionarioDTO salarioAtualizado = calcularSalarioService
                .calcularSalarioFuncionario(funcionarioModel.getCpf());

        Assertions.assertEquals(672.0, salarioAtualizado.getNovoSalario());
        Assertions.assertEquals(72.0, salarioAtualizado.getReajusteGanho());
        Assertions.assertEquals("12%", salarioAtualizado.getPercentual());
    }

    @Test
    void deveriaAumentar10PorCentoDoSalarioDe801Ate1200() throws ParseException {

        FuncionarioModel funcionarioModel = ModelDadosTest.buildFuncionarioModel();
        funcionarioModel.setSalario(1000.0);

        Mockito.when(funcionarioRepository.getReferenceByCpf(funcionarioModel.getCpf()))
                .thenReturn(funcionarioModel);

        Mockito.when(funcionarioRepository.save(funcionarioModel)).thenReturn(funcionarioModel);

        DadosRetornoCalculoFuncionarioDTO salarioAtualizado = calcularSalarioService
                .calcularSalarioFuncionario(funcionarioModel.getCpf());

        Assertions.assertEquals(1100.0, salarioAtualizado.getNovoSalario());
        Assertions.assertEquals(100.0, salarioAtualizado.getReajusteGanho());
        Assertions.assertEquals("10%", salarioAtualizado.getPercentual());
    }

    @Test
    void deveriaAumentar7PorCentoDoSalarioDe1201Ate2000() throws ParseException {

        FuncionarioModel funcionarioModel = ModelDadosTest.buildFuncionarioModel();
        funcionarioModel.setSalario(1300.0);

        Mockito.when(funcionarioRepository.getReferenceByCpf(funcionarioModel.getCpf()))
                .thenReturn(funcionarioModel);

        Mockito.when(funcionarioRepository.save(funcionarioModel)).thenReturn(funcionarioModel);

        DadosRetornoCalculoFuncionarioDTO salarioAtualizado = calcularSalarioService
                .calcularSalarioFuncionario(funcionarioModel.getCpf());

        Assertions.assertEquals(1391.0, salarioAtualizado.getNovoSalario());
        Assertions.assertEquals(91.0, salarioAtualizado.getReajusteGanho());
        Assertions.assertEquals("7%", salarioAtualizado.getPercentual());
    }

    @Test
    void deveriaAumentar4PorCentoDoSalarioAcimaDe2000() throws ParseException {

        FuncionarioModel funcionarioModel = ModelDadosTest.buildFuncionarioModel();
        funcionarioModel.setSalario(2500.0);

        Mockito.when(funcionarioRepository.getReferenceByCpf(funcionarioModel.getCpf()))
                .thenReturn(funcionarioModel);

        Mockito.when(funcionarioRepository.save(funcionarioModel)).thenReturn(funcionarioModel);

        DadosRetornoCalculoFuncionarioDTO salarioFuncionarioAtualizado = calcularSalarioService
                .calcularSalarioFuncionario(funcionarioModel.getCpf());

        Assertions.assertEquals(2600.0, salarioFuncionarioAtualizado.getNovoSalario());
        Assertions.assertEquals(100.0, salarioFuncionarioAtualizado.getReajusteGanho());
        Assertions.assertEquals("4%", salarioFuncionarioAtualizado.getPercentual());
    }


}
