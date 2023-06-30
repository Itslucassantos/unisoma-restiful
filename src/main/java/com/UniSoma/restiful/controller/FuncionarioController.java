package com.UniSoma.restiful.controller;

import com.UniSoma.restiful.dto.DadosCadastroFuncionarioDTO;
import com.UniSoma.restiful.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFuncionarioDTO dados) {
        return ResponseEntity.ok(this.funcionarioService.cadastrar(dados));
    }

    @GetMapping("/listar")
    @Transactional
    public ResponseEntity<Page<DadosCadastroFuncionarioDTO>> listar(
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "20") int size) {
        return new ResponseEntity<>(this.funcionarioService.listar(page, size), HttpStatus.OK);
    }


}
