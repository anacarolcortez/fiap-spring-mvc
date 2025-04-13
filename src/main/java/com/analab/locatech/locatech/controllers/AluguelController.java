package com.analab.locatech.locatech.controllers;

import com.analab.locatech.locatech.controllers.docs.AluguelApiDoc;
import com.analab.locatech.locatech.dtos.AluguelRequestDTO;
import com.analab.locatech.locatech.entities.Aluguel;
import com.analab.locatech.locatech.services.AluguelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alugueis")
@Tag(name = "Alugueis", description = "Recurso para gestão de alugueis")
public class AluguelController implements AluguelApiDoc {

    private static final Logger logger = LoggerFactory.getLogger(AluguelController.class);

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Aluguel>> findAllAlugueis(
            @RequestParam("page") int page,
            @RequestParam("size") int size){
        logger.info("GET /alugueis");
        var alugueis = this.aluguelService.findAllAlugueis(page, size);
        return ResponseEntity.ok(alugueis);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Aluguel> findAluguel(@PathVariable("id") Long id){
        logger.info("GET /alugueis/" + id);
        var aluguel = this.aluguelService.findAluguelById(id);
        return aluguel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Override
    @PostMapping()
    public ResponseEntity<Void> saveAluguel(@Valid @RequestBody AluguelRequestDTO aluguel){
        logger.info("POST /alugueis");
        this.aluguelService.saveAluguel(aluguel);
        return ResponseEntity.status(201).build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel(@PathVariable("id") Long id,
                                              @Valid @RequestBody AluguelRequestDTO aluguel){
        logger.info("PUT /alugueis/" + id);
        this.aluguelService.updateAluguel(aluguel, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluguel(@PathVariable("id") Long id){
        logger.info("DELETE /alugueis/" + id);
        this.aluguelService.deleteAluguel(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
