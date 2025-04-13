package com.analab.locatech.locatech.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record VeiculoRequestDTO(

        @NotNull(message = "A marca do veículo não pode ser nula")
        String marca,

        @NotNull(message = "O modelo do veículo não pode ser nulo")
        String modelo,

        @NotNull(message = "A placa do veículo não pode ser nula")
        String placa,

        int ano,

        String cor,

        @NotNull(message = "O valor da diária não pode ser nulo")
        BigDecimal valorDiaria
) {
}
