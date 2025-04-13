package com.analab.locatech.locatech.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequestDTO(

        @Schema(description = "ID do cliente que aluga o veículo")
        @NotNull(message = "O id da pessoa não pode ser nulo")
        Long pessoaId,

        @Schema(description = "ID do veículo alugado")
        @NotNull(message = "O id do veículo não pode ser nulo")
        Long veiculoId,

        @Schema(description = "Data de início do aluguel do veículo")
        @NotNull(message = "A data de início deve ser informada")
        LocalDate dataInicio,

        @Schema(description = "Data de término do aluguel do veículo")
        @NotNull(message = "A data de fim deve ser informada")
        LocalDate dataFim
) {
}
