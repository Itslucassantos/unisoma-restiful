package com.UniSoma.restiful.funcionario;

import com.UniSoma.restiful.dto.DadosCadastroFuncionarioDTO;
import com.UniSoma.restiful.massaDeDados.DtoDadosTest;
import com.UniSoma.restiful.massaDeDados.ModelDadosTest;
import com.UniSoma.restiful.model.FuncionarioModel;
import com.UniSoma.restiful.repository.FuncionarioRepository;
import com.UniSoma.restiful.service.FuncionarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

public class FuncionarioServiceTest {

    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Captor
    private ArgumentCaptor<FuncionarioModel> captor;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.funcionarioService = new FuncionarioService(funcionarioRepository);
    }

    @Test
    void deveriaCadastrarFuncionarioQueNaoExiste() {

        DadosCadastroFuncionarioDTO dadosCadastroFuncionarioDTO = DtoDadosTest.buildDadosCadastroFuncionarioDTO();

        Mockito.when(funcionarioRepository.getReferenceByCpf(dadosCadastroFuncionarioDTO.getCpf()))
                .thenReturn(null);

        FuncionarioModel funcionarioModel = new FuncionarioModel(dadosCadastroFuncionarioDTO);

        Mockito.when(funcionarioRepository.save(funcionarioModel))
                .thenReturn(funcionarioModel);

        funcionarioService.cadastrar(dadosCadastroFuncionarioDTO);

        Mockito.verify(funcionarioRepository).save(captor.capture());
        FuncionarioModel funcionarioModelSalvo = captor.getValue();

        Assertions.assertEquals(dadosCadastroFuncionarioDTO.getNome(), funcionarioModelSalvo.getNome());
        Assertions.assertEquals(dadosCadastroFuncionarioDTO.getCpf(), funcionarioModelSalvo.getCpf());
        Assertions.assertEquals(dadosCadastroFuncionarioDTO.getDataNascimento(), funcionarioModelSalvo.getDataNascimento());
        Assertions.assertEquals(dadosCadastroFuncionarioDTO.getTelefone(), funcionarioModelSalvo.getTelefone());
        Assertions.assertEquals(dadosCadastroFuncionarioDTO.getEndereco(), funcionarioModelSalvo.getEndereco());
        Assertions.assertEquals(dadosCadastroFuncionarioDTO.getSalario(), funcionarioModelSalvo.getSalario());
    }

    @Test
    void naoDeveriaCadastrarQuandoFuncionarioExistir() {

        FuncionarioModel funcionarioModel = ModelDadosTest.buildFuncionarioModel();

        Mockito.when(funcionarioRepository.getReferenceByCpf(funcionarioModel.getCpf()))
                .thenReturn(funcionarioModel);

        DadosCadastroFuncionarioDTO dadosCadastroFuncionarioDTO = new DadosCadastroFuncionarioDTO(funcionarioModel);

        try {
            funcionarioService.cadastrar(dadosCadastroFuncionarioDTO);
            fail();
        } catch (Exception e) {
            Assertions.assertEquals(" Funcionário com esse cpf já existe! ",
                    e.getMessage());
        }
    }

    @Test
    void deveriaListarTodos() {

        ArrayList<FuncionarioModel> funcionarios = new ArrayList<>();

        FuncionarioModel funcionarioModel = ModelDadosTest.buildFuncionarioModel();
        funcionarios.add(funcionarioModel);

        Page<FuncionarioModel> funcionariosPage = new PageImpl<>(funcionarios);

        Mockito.when(funcionarioRepository.findAll(Mockito.any(Pageable.class))).thenReturn(funcionariosPage);

        Page<DadosCadastroFuncionarioDTO> funcionarioDTOS = funcionarioService.listar(0, 10);
        Assertions.assertEquals(1, funcionarioDTOS.getContent().size());
        DadosCadastroFuncionarioDTO funcionarioDTO = funcionarioDTOS.getContent().get(0);
        Assertions.assertEquals(funcionarioModel.getNome(), funcionarioDTO.getNome());
        Assertions.assertEquals(funcionarioModel.getCpf(), funcionarioDTO.getCpf());
        Assertions.assertEquals(funcionarioModel.getEndereco(), funcionarioDTO.getEndereco());
    }


}
