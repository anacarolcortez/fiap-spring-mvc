package com.analab.locatech.locatech.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record PessoaRequestDTO(

        @Schema(description = "Nome do cliente")
        @NotNull(message = "O nome da pessoa não pode ser nulo")
        String nome,

        @Schema(description = "CPF do cliente")
        @NotNull(message = "O cpf da pessoa não pode ser nulo")
        String cpf,

        @Schema(description = "Telefone do cliente")
        @NotNull(message = "O telefone da pessoa não pode ser nulo")
        String telefone,

        @Schema(description = "E-mail do cliente")
        @NotNull(message = "O email da pessoa não pode ser nulo")
        @Email(message = "E-mail precisa ter formato válido")
        String email
) {
}
