package com.analab.locatech.locatech.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record VeiculoRequestDTO(

        @Schema(description = "Marca do veículo")
        @NotNull(message = "A marca do veículo não pode ser nula")
        String marca,

        @Schema(description = "Modelo do veículo")
        @NotNull(message = "O modelo do veículo não pode ser nulo")
        String modelo,

        @Schema(description = "Placa do veículo")
        @NotNull(message = "A placa do veículo não pode ser nula")
        String placa,

        @Schema(description = "Ano de fabricação do veículo")
        int ano,

        @Schema(description = "Cor do veículo")
        String cor,

        @Schema(description = "Preço da diária do veículo")
        @NotNull(message = "O valor da diária não pode ser nulo")
        BigDecimal valorDiaria
) {
}
