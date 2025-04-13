package com.analab.locatech.locatech.dtos;

import jakarta.validation.constraints.NotNull;

public record PessoaRequestDTO(

        @NotNull(message = "O nome da pessoa não pode ser nulo")
        String nome,

        @NotNull(message = "O cpf da pessoa não pode ser nulo")
        String cpf,

        @NotNull(message = "O telefone da pessoa não pode ser nulo")
        String telefone,

        @NotNull(message = "O email da pessoa não pode ser nulo")
        String email
) {
}
