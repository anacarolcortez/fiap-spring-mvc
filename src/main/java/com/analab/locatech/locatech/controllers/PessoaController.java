package com.analab.locatech.locatech.controllers;

import com.analab.locatech.locatech.controllers.docs.PessoaApiDoc;
import com.analab.locatech.locatech.dtos.PessoaRequestDTO;
import com.analab.locatech.locatech.entities.Pessoa;
import com.analab.locatech.locatech.services.PessoaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@Tag(name = "Pessoas", description = "Recurso para gestão de pessoas (clientes)")
public class PessoaController implements PessoaApiDoc {

    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllPessoas(
            @RequestParam("page") int page,
            @RequestParam("size") int size){
        logger.info("GET /pessoas");
        var pessoas = this.pessoaService.findAllPessoas(page, size);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findPessoa(@PathVariable("id") Long id){
        logger.info("GET /pessoas/" + id);
        var pessoa = this.pessoaService.findPessoaById(id);
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Override
    @PostMapping()
    public ResponseEntity<Void> savePessoa(@Valid @RequestBody PessoaRequestDTO pessoa){
        logger.info("POST /pessoas");
        this.pessoaService.savePessoa(pessoa);
        return ResponseEntity.status(201).build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePessoa(@PathVariable("id") Long id,
                                             @Valid @RequestBody PessoaRequestDTO pessoa){
        logger.info("PUT /pessoas/" + id);
        this.pessoaService.updatePessoa(pessoa, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable("id") Long id){
        logger.info("DELETE /pessoas/" + id);
        this.pessoaService.deletePessoa(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
