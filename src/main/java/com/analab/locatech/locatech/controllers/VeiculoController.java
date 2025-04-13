package com.analab.locatech.locatech.controllers;

import com.analab.locatech.locatech.controllers.docs.VeiculoApiDoc;
import com.analab.locatech.locatech.dtos.VeiculoRequestDTO;
import com.analab.locatech.locatech.entities.Veiculo;
import com.analab.locatech.locatech.services.VeiculoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@Tag(name = "Veículos", description = "Recurso para gestão de veículos")
public class VeiculoController implements VeiculoApiDoc {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Veiculo>> findAllVeiculos(int page, int size) {
        logger.info("GET /veículos");
        var veiculos = veiculoService.findAllVeiculos(page, size);
        return ResponseEntity.ok(veiculos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findVeiculos(Long id) {
        logger.info("GET /veiculos/" + id);
        var veiculo = veiculoService.findVeiculoById(id);
        return veiculo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> saveVeiculo(@Valid @RequestBody VeiculoRequestDTO veiculo) {
        logger.info("POST /veiculos");
        veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.status(201).build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(Long id, @Valid @RequestBody VeiculoRequestDTO veiculo) {
        logger.info("PUT /veiculos/" + id);
        veiculoService.updateVeiculo(veiculo, id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(Long id) {
        logger.info("DELETE /veiculos/" + id);
        veiculoService.deleteVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}

