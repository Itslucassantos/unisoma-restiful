package com.UniSoma.restiful.service;

import com.UniSoma.restiful.dto.DadosRetornoCalculoFuncionarioDTO;
import com.UniSoma.restiful.exceptions.FuncionarioException;
import com.UniSoma.restiful.model.FuncionarioModel;
import com.UniSoma.restiful.repository.FuncionarioRepository;
import com.UniSoma.restiful.util.PadraoFormato;
import com.UniSoma.restiful.util.MascaraCpf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class CalcularSalarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public CalcularSalarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public DadosRetornoCalculoFuncionarioDTO calcularSalarioFuncionario(String cpf) throws ParseException {

        FuncionarioModel funcionarioModel = this.funcionarioRepository.getReferenceByCpf(cpf);

        if(funcionarioModel == null) {
            throw new FuncionarioException(" Funcionário com esse cpf não existe! ");
        }

        double reajusteGanho = 0.0;
        double novoSalario = 0.0;
        String percentual = "";
        double salarioAntigo = funcionarioModel.getSalario();

        if(funcionarioModel.getSalario() > 0 && funcionarioModel.getSalario() <= 400.0) {
            percentual = "15%";
            reajusteGanho = salarioAntigo * 0.15;
        } else if(funcionarioModel.getSalario() >= 400.01 && funcionarioModel.getSalario() <= 800.0) {
            percentual = "12%";
            reajusteGanho = salarioAntigo * 0.12;
        } else if(funcionarioModel.getSalario() >= 800.01 && funcionarioModel.getSalario() <= 1200.0) {
            percentual = "10%";
            reajusteGanho = salarioAntigo * 0.10;
        } else if(funcionarioModel.getSalario() >= 1200.01 && funcionarioModel.getSalario() <= 2000.0) {
            percentual = "7%";
            reajusteGanho = salarioAntigo * 0.07;
        } else if (funcionarioModel.getSalario() > 2000.0) {
            percentual = "4%";
            reajusteGanho = salarioAntigo * 0.04;
        }

        novoSalario = salarioAntigo + reajusteGanho;
        double novoSalarioFormatado = PadraoFormato.formatar(novoSalario);
        double reajusteGanhoFormatado = PadraoFormato.formatar(reajusteGanho);
        String cpfFormatado = MascaraCpf.formatarCpf(cpf);

        funcionarioModel.setSalario(novoSalarioFormatado);
        this.funcionarioRepository.save(funcionarioModel);
        return new DadosRetornoCalculoFuncionarioDTO(cpfFormatado, novoSalarioFormatado, reajusteGanhoFormatado, percentual);
    }


}
