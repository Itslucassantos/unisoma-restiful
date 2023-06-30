package com.UniSoma.restiful.repository;

import com.UniSoma.restiful.model.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long> {
    FuncionarioModel getReferenceByCpf(String cpf);

    FuncionarioModel save(FuncionarioModel funcionarioModel);
}
