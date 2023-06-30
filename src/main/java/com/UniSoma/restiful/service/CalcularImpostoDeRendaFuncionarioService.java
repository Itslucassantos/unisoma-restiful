package com.UniSoma.restiful.service;

import com.UniSoma.restiful.dto.DadosRetornoImpostoDeRendaDTO;
import com.UniSoma.restiful.exceptions.FuncionarioException;
import com.UniSoma.restiful.util.PadraoFormato;
import com.UniSoma.restiful.util.MascaraCpf;
import com.UniSoma.restiful.model.FuncionarioModel;
import com.UniSoma.restiful.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class CalcularImpostoDeRendaFuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public CalcularImpostoDeRendaFuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    String imposto = "";
    double valorImpostoRenda = 0.0;

    public DadosRetornoImpostoDeRendaDTO calcularImpostoDeRendaFuncionario(String cpf) throws ParseException {

        FuncionarioModel funcionarioModel = funcionarioRepository.getReferenceByCpf(cpf);

        if( funcionarioModel == null) {
            throw new FuncionarioException(" Não é possível calcular imposto de renda, pois funcionário com esse cpf não existe! ");
        }

        String cpfFormatado = MascaraCpf.formatarCpf(cpf);

        if(funcionarioModel.getSalario() > 0.0 && funcionarioModel.getSalario() <= 2000.0) {
            imposto = " Isento ";
            return new DadosRetornoImpostoDeRendaDTO(cpfFormatado, imposto);
        } else if(funcionarioModel.getSalario() >= 2000.01 && funcionarioModel.getSalario() <= 3000.0) {
            valorImpostoRenda = funcionarioModel.getSalario() * 0.08;
        } else if(funcionarioModel.getSalario() >= 3000.01 && funcionarioModel.getSalario() <= 4500.0) {
            valorImpostoRenda = funcionarioModel.getSalario() * 0.18;
        } else if(funcionarioModel.getSalario() > 4500.0) {
            valorImpostoRenda = funcionarioModel.getSalario() * 0.28;
        }

        double valorImpostoRendaFormatado = PadraoFormato.formatar(valorImpostoRenda);
        imposto = "R$ " + valorImpostoRendaFormatado;

        return new DadosRetornoImpostoDeRendaDTO(cpfFormatado, imposto);
    }

}
