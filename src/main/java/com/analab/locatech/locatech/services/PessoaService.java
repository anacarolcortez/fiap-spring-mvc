package com.analab.locatech.locatech.services;

import com.analab.locatech.locatech.dtos.PessoaRequestDTO;
import com.analab.locatech.locatech.entities.Pessoa;
import com.analab.locatech.locatech.repositories.PessoaRepository;
import com.analab.locatech.locatech.services.exceptions.ConstraintException;
import com.analab.locatech.locatech.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findAllPessoas(int page, int size) {
        int offset = (page -1) * size;
        return pessoaRepository.findAll(size, offset);
    }

    public Optional<Pessoa> findPessoaById(Long id){
        return Optional.ofNullable(this.pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada, id: " + id)));
    }

    public void savePessoa(PessoaRequestDTO dto){
        var pessoa = mapper(dto);
        var save = this.pessoaRepository.save(pessoa);
        Assert.state(save == 1, "Erro ao salvar pessoa: " + pessoa.getNome());
    }

    public void updatePessoa(PessoaRequestDTO dto, Long id){
        var pessoa = mapper(dto);
        var update = this.pessoaRepository.update(pessoa, id);
        if (update == 0){
            throw new ResourceNotFoundException("Pessoa não encontrada, id: " + id);
        }
    }

    public void deletePessoa(Long id){
        try {
            var delete = this.pessoaRepository.delete(id);
            if (delete == 0){
                throw new ResourceNotFoundException("Pessoa não encontrada, id: " + id);
            }
        } catch (Exception e){
            throw new ConstraintException("Pessoa não pode ser excluída, id: " + id);
        }
    }

    private Pessoa mapper(PessoaRequestDTO dto){
        return new Pessoa(dto);
    }
}
