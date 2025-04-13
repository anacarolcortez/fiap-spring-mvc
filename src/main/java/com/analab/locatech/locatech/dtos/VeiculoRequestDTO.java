package com.analab.locatech.locatech.dtos;

import java.math.BigDecimal;

public record VeiculoRequestDTO(
        String marca,
        String modelo,
        String placa,
        int ano,
        String cor,
        BigDecimal valorDiaria
) {
}
