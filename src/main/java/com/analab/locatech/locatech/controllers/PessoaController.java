package com.analab.locatech.locatech.controllers;

import com.analab.locatech.locatech.entities.Pessoa;
import com.analab.locatech.locatech.services.PessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllPessoas(
            @RequestParam("page") int page,
            @RequestParam("size") int size){
        logger.info("GET /pessoas");
        var Pessoas = this.pessoaService.findAllPessoas(page, size);
        return ResponseEntity.ok(Pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findPessoa(@PathVariable("id") Long id){
        logger.info("GET /pessoas/" + id);
        var pessoa = this.pessoaService.findPessoaById(id);
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping()
    public ResponseEntity<Void> savePessoa(@RequestBody Pessoa pessoa){
        logger.info("POST /pessoas");
        this.pessoaService.savePessoa(pessoa);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePessoa(@PathVariable("id") Long id,
                                              @RequestBody Pessoa pessoa){
        logger.info("PUT /pessoas/" + id);
        this.pessoaService.updatePessoa(pessoa, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable("id") Long id){
        logger.info("DELETE /pessoas/" + id);
        this.pessoaService.deletePessoa(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
