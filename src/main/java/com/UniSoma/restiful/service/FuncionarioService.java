package com.UniSoma.restiful.service;

import com.UniSoma.restiful.dto.DadosCadastroFuncionarioDTO;
import com.UniSoma.restiful.exceptions.FuncionarioException;
import com.UniSoma.restiful.model.FuncionarioModel;
import com.UniSoma.restiful.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.validation.Valid;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }
    
    public DadosCadastroFuncionarioDTO cadastrar(@Valid DadosCadastroFuncionarioDTO dados) {

        FuncionarioModel funcionarioExiste = funcionarioRepository.getReferenceByCpf(dados.getCpf());

        if (funcionarioExiste != null) {
            throw new FuncionarioException(" Funcionário com esse cpf já existe! ");
        }

        FuncionarioModel funcionarioModel = new FuncionarioModel(dados);
        this.funcionarioRepository.save(funcionarioModel);
        return new DadosCadastroFuncionarioDTO(funcionarioModel);
    }

    public Page<DadosCadastroFuncionarioDTO> listar(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.funcionarioRepository.findAll(pageRequest)
                .map(DadosCadastroFuncionarioDTO::new);
    }

}
