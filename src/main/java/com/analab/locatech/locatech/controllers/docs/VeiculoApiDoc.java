package com.analab.locatech.locatech.controllers.docs;

import com.analab.locatech.locatech.dtos.ValidationErrorDTO;
import com.analab.locatech.locatech.dtos.VeiculoRequestDTO;
import com.analab.locatech.locatech.entities.Veiculo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface VeiculoApiDoc {

    @Operation(
            summary = "Listagem",
            description = "Busca todos os veículos com paginação"
    )
    ResponseEntity<List<Veiculo>> findAllVeiculos(@RequestParam("page") int page,
                                                  @RequestParam("size") int size);

    @Operation(
            summary = "Busca",
            description = "Busca um veículo por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    ResponseEntity<Veiculo> findVeiculos(@PathVariable("id") Long id);

    @Operation(
            summary = "Criação",
            description = "Cria o registro de um veículo",
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
                        "modelo: O modelo do veículo não pode ser nulo",
                        "valorDiaria: O valor da diária não pode ser nulo",
                        "placa: A placa do veículo não pode ser nula",
                        "marca: A marca do veículo não pode ser nula"
                      ],
                      "status": 400
                    }
                    """)
                            )
                    )
            }
    )
    ResponseEntity<Void> saveVeiculo(@Valid @RequestBody VeiculoRequestDTO veiculo);

    @Operation(
            summary = "Atualização",
            description = "Atualiza o registro de um veículo, por ID",
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
                        "modelo: O modelo do veículo não pode ser nulo",
                        "valorDiaria: O valor da diária não pode ser nulo",
                        "placa: A placa do veículo não pode ser nula",
                        "marca: A marca do veículo não pode ser nula"
                      ],
                      "status": 400
                    }
                    """)
                            )
                    )
            }
    )
    ResponseEntity<Void> updateVeiculo(@PathVariable("id") Long id,
                                       @Valid @RequestBody VeiculoRequestDTO veiculo);

    @Operation(
            summary = "Exclusão",
            description = "Exclui o registro de um veículo, por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    ResponseEntity<Void> deleteVeiculo(@PathVariable("id") Long id);
}
