package com.analab.locatech.locatech.controllers.docs;

import com.analab.locatech.locatech.dtos.AluguelRequestDTO;
import com.analab.locatech.locatech.dtos.ValidationErrorDTO;
import com.analab.locatech.locatech.entities.Aluguel;
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

public interface AluguelApiDoc {

    @Operation(
            summary = "Listagem",
            description = "Busca todos os alugueis com paginação"
    )
    ResponseEntity<List<Aluguel>> findAllAlugueis(@RequestParam("page") int page,
                                                  @RequestParam("size") int size);

    @Operation(
            summary = "Busca",
            description = "Busca um aluguel por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    ResponseEntity<Aluguel> findAluguel(@PathVariable("id") Long id);

    @Operation(
            summary = "Criação",
            description = "Cria o registro de um aluguel",
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
                        "pessoaId: O id da pessoa não pode ser nulo",
                        "dataInicio: A data de início deve ser informada",
                        "dataFim: A data de fim deve ser informada",
                        "veiculoId: O id do veículo não pode ser nulo"
                      ],
                      "status": 400
                    }
                    """)
                            )
                    )
            }
    )
    ResponseEntity<Void> saveAluguel(@Valid @RequestBody AluguelRequestDTO aluguel);

    @Operation(
            summary = "Atualização",
            description = "Atualiza o registro de um aluguel, por ID",
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
                        "pessoaId: O id da pessoa não pode ser nulo",
                        "dataInicio: A data de início deve ser informada",
                        "dataFim: A data de fim deve ser informada",
                        "veiculoId: O id do veículo não pode ser nulo"
                      ],
                      "status": 400
                    }
                    """)
                            )
                    )
            }
    )
    ResponseEntity<Void> updateAluguel(@PathVariable("id") Long id,
                                       @Valid @RequestBody AluguelRequestDTO aluguel);

    @Operation(
            summary = "Exclusão",
            description = "Exclui o registro de um aluguel, por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    ResponseEntity<Void> deleteAluguel(@PathVariable("id") Long id);
}
