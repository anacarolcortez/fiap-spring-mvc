package com.analab.locatech.locatech.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequestDTO(

        @NotNull(message = "O id da pessoa não pode ser nulo")
        Long pessoaId,

        @NotNull(message = "O id do veículo não pode ser nulo")
        Long veiculoId,

        @NotNull(message = "A data de início deve ser informada")
        LocalDate dataInicio,

        @NotNull(message = "A data de fim deve ser informada")
        LocalDate dataFim
) {
}
