package com.analab.locatech.locatech.entities;

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
    private String pessoaCPF;
    private String pessoaNome;
    private Long veiculoId;
    private String veiculoModelo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private BigDecimal valorTotal;
}
