package com.analab.locatech.locatech.entities;

import com.analab.locatech.locatech.dtos.PessoaRequestDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Pessoa {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public Pessoa(PessoaRequestDTO dto) {
        nome = dto.nome();
        cpf = dto.cpf();
        telefone = dto.telefone();
        email = dto.email();
    }
}
