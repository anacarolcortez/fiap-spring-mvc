package com.analab.locatech.locatech.services;

import com.analab.locatech.locatech.dtos.VeiculoRequestDTO;
import com.analab.locatech.locatech.entities.Veiculo;
import com.analab.locatech.locatech.repositories.VeiculoRepository;
import com.analab.locatech.locatech.services.exceptions.ConstraintException;
import com.analab.locatech.locatech.services.exceptions.ResourceNotFoundException;
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
        return Optional.ofNullable(this.veiculoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado, id: " + id)));
    }

    public void saveVeiculo(VeiculoRequestDTO dto){
        var veiculo = mapper(dto);
        var save = this.veiculoRepository.save(veiculo);
        Assert.state(save == 1, "Erro ao salvar veículo: " + veiculo.getMarca());
    }

    public void updateVeiculo(VeiculoRequestDTO dto, Long id){
        var veiculo = mapper(dto);
        var update = this.veiculoRepository.update(veiculo, id);
        if (update == 0){
            throw new ResourceNotFoundException("Veículo não encontrado, id: " + id);
        }
    }

    public void deleteVeiculo(Long id){
        try {
            var delete = this.veiculoRepository.delete(id);
            if (delete == 0){
                throw new ResourceNotFoundException("Veículo não encontrado, id: " + id);
            }
        } catch (Exception e){
            throw new ConstraintException("Veículo não pode ser excluído, id: " + id);
        }
    }

    private Veiculo mapper(VeiculoRequestDTO dto){
        return new Veiculo(dto);
    }
}
