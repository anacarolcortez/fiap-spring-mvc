package com.analab.locatech.locatech.entities;

import com.analab.locatech.locatech.dtos.VeiculoRequestDTO;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Veiculo {

    private Long id;
    private String marca;
    private String modelo;
    private String placa;
    private int ano;
    private String cor;
    private BigDecimal valorDiaria;

    public Veiculo(VeiculoRequestDTO dto) {
        marca = dto.marca();
        modelo = dto.modelo();
        placa = dto.placa();
        ano = dto.ano();
        cor = dto.cor();
        valorDiaria = dto.valorDiaria();
    }
}
