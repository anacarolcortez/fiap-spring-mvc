package com.analab.locatech.locatech.dtos;

public record PessoaRequestDTO(
        String nome,
        String cpf,
        String telefone,
        String email
) {
}
