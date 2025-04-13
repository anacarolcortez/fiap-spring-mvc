package com.analab.locatech.locatech.services;

import com.analab.locatech.locatech.entities.Aluguel;
import com.analab.locatech.locatech.repositories.AluguelRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;

    public AluguelService(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }

    public List<Aluguel> findAllAlugueis(int page, int size) {
        int offset = (page -1) * size;
        return aluguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findAluguelById(Long id){
        return this.aluguelRepository.findById(id);
    }

    public void saveAluguel(Aluguel aluguel){
        var save = this.aluguelRepository.save(aluguel);
        Assert.state(save == 1, "Erro ao salvar aluguel de: " + aluguel.getPessoaNome());
    }

    public void updateAluguel(Aluguel aluguel, Long id){
        var update = this.aluguelRepository.update(aluguel, id);
        if (update == 0){
            throw new RuntimeException("Aluguel não encontrada, id: " + id);
        }
    }

    public void deleteAluguel(Long id){
        var delete = this.aluguelRepository.delete(id);
        if (delete == 0){
            throw new RuntimeException("Aluguel não encontrado, id: " + id);
        }
    }
}
