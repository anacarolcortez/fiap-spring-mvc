package com.analab.locatech.locatech.services;

import com.analab.locatech.locatech.entities.Veiculo;
import com.analab.locatech.locatech.repositories.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> findAllVeiculos(int page, int size) {
        int offset = (page -1) * size;
        return veiculoRepository.findAll(size, offset);
    }

    public Optional<Veiculo> findVeiculoById(Long id){
        return this.veiculoRepository.findById(id);
    }

    public void saveVeiculo(Veiculo veiculo){
        var save = this.veiculoRepository.save(veiculo);
        Assert.state(save == 1, "Erro ao salvar veículo: " + veiculo.getMarca());
    }

    public void updateVeiculo(Veiculo veiculo, Long id){
        var update = this.veiculoRepository.update(veiculo, id);
        if (update == 0){
            throw new RuntimeException("Veículo não encontrado, id: " + id);
        }
    }

    public void deleteVeiculo(Long id){
        try {
            var delete = this.veiculoRepository.delete(id);
            if (delete == 0){
                throw new RuntimeException("Veículo não encontrado, id: " + id);
            }
        } catch (Exception e){
            throw new RuntimeException("Veículo não pode ser excluído, id: " + id);
        }
    }
}
