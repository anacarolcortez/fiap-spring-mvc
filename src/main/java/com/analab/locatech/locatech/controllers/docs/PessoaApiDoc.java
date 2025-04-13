package com.analab.locatech.locatech.controllers.docs;

import com.analab.locatech.locatech.dtos.PessoaRequestDTO;
import com.analab.locatech.locatech.dtos.ValidationErrorDTO;
import com.analab.locatech.locatech.entities.Pessoa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PessoaApiDoc {

    @Operation(
            summary = "Listagem",
            description = "Busca todos os clientes com paginação"
    )
    ResponseEntity<List<Pessoa>> findAllPessoas(@RequestParam("page") int page,
                                                       @RequestParam("size") int size);

    @Operation(
            summary = "Busca",
            description = "Busca um cliente por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    ResponseEntity<Pessoa> findPessoa(@PathVariable("id") Long id);

    @Operation(
            summary = "Criação",
            description = "Cria o registro de um cliente",
            responses = {
                    @ApiResponse(responseCode = "201", description = "CREATED"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "BAD REQUEST",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ValidationErrorDTO.class),
                                    examples = @ExampleObject(value = """
                    {
                      "errors": [
                        "email: O email da pessoa não pode ser nulo",
                        "telefone: O telefone da pessoa não pode ser nulo",
                        "nome: O nome da pessoa não pode ser nulo",
                        "cpf: O cpf da pessoa não pode ser nulo"
                      ],
                      "status": 400
                    }
                    """)
                            )
                    )
            }
    )
    ResponseEntity<Void> savePessoa(@Valid @RequestBody PessoaRequestDTO pessoa);

    @Operation(
            summary = "Atualização",
            description = "Atualiza o registro de um cliente, por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "NO CONTENT"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "BAD REQUEST",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ValidationErrorDTO.class),
                                    examples = @ExampleObject(value = """
                    {
                      "errors": [
                        "email: O email da pessoa não pode ser nulo",
                        "telefone: O telefone da pessoa não pode ser nulo",
                        "nome: O nome da pessoa não pode ser nulo",
                        "cpf: O cpf da pessoa não pode ser nulo"
                      ],
                      "status": 400
                    }
                    """)
                            )
                    )
            }
    )
    ResponseEntity<Void> updatePessoa(@PathVariable("id") Long id,
                                      @Valid @RequestBody PessoaRequestDTO pessoa);

    @Operation(
            summary = "Exclusão",
            description = "Exclui o registro de um cliente, por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    ResponseEntity<Void> deletePessoa(@PathVariable("id") Long id);
}
