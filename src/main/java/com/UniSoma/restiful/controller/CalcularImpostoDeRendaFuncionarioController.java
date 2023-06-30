package com.UniSoma.restiful.controller;

import com.UniSoma.restiful.service.CalcularImpostoDeRendaFuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("calcularImpostoDeRendaFuncionario")
public class CalcularImpostoDeRendaFuncionarioController {

    private final CalcularImpostoDeRendaFuncionarioService impostoDeRendaFuncionarioService;

    @Autowired
    public CalcularImpostoDeRendaFuncionarioController(CalcularImpostoDeRendaFuncionarioService impostoDeRendaFuncionarioService) {
        this.impostoDeRendaFuncionarioService = impostoDeRendaFuncionarioService;
    }

    @PutMapping("/{cpf}")
    @Transactional
    public ResponseEntity calcularImpostoDeRendaFuncionario(@PathVariable("cpf") @Valid String cpf) throws ParseException {
        return ResponseEntity.ok(this.impostoDeRendaFuncionarioService.calcularImpostoDeRendaFuncionario(cpf));
    }

}
