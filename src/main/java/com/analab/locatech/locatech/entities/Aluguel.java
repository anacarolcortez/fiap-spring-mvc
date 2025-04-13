package com.analab.locatech.locatech.entities;

import com.analab.locatech.locatech.dtos.AluguelRequestDTO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Aluguel {
    private Long id;
    private Long pessoaId;
    private String pessoaCpf;
    private String pessoaNome;
    private Long veiculoId;
    private String veiculoModelo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private BigDecimal valorTotal;

    public Aluguel(AluguelRequestDTO dto, BigDecimal total) {
        this.pessoaId = dto.pessoaId();
        this.veiculoId = dto.veiculoId();
        this.dataInicio = dto.dataInicio();
        this.dataFim = dto.dataFim();
        this.valorTotal = total;
    }
}
