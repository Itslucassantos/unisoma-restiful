package com.UniSoma.restiful.controller;

import com.UniSoma.restiful.service.CalcularSalarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("calcularSalarioFuncionario")
public class CalcularSalarioController {

    private final CalcularSalarioService calcularSalarioService;

    @Autowired
    public CalcularSalarioController(CalcularSalarioService calcularSalarioService) {
        this.calcularSalarioService = calcularSalarioService;
    }

    @PutMapping("/{cpf}")
    @Transactional
    public ResponseEntity calcularSalarioFuncionario(@PathVariable("cpf") @Valid String cpf) throws ParseException {
        return ResponseEntity.ok(this.calcularSalarioService.calcularSalarioFuncionario(cpf));
    }

}
