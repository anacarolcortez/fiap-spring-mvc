package com.analab.locatech.locatech.controllers;

import com.analab.locatech.locatech.entities.Veiculo;
import com.analab.locatech.locatech.services.VeiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> findAllVeiculos(
            @RequestParam("page") int page,
            @RequestParam("size") int size){
        logger.info("GET /veículos");
        var veiculos = this.veiculoService.findAllVeiculos(page, size);
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findVeiculos(@PathVariable("id") Long id){
        logger.info("GET /veiculos/" + id);
        var veiculo = this.veiculoService.findVeiculoById(id);
        return veiculo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping()
    public ResponseEntity<Void> saveVeiculo(@RequestBody Veiculo veiculo){
        logger.info("POST /veiculos");
        this.veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(@PathVariable("id") Long id,
                                              @RequestBody Veiculo veiculo){
        logger.info("PUT /veiculos/" + id);
        this.veiculoService.updateVeiculo(veiculo, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable("id") Long id){
        logger.info("DELETE /veiculos/" + id);
        this.veiculoService.deleteVeiculo(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
