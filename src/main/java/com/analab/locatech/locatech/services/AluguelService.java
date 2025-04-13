package com.analab.locatech.locatech.services;

import com.analab.locatech.locatech.dtos.AluguelRequestDTO;
import com.analab.locatech.locatech.entities.Aluguel;
import com.analab.locatech.locatech.repositories.AluguelRepository;
import com.analab.locatech.locatech.repositories.VeiculoRepository;
import com.analab.locatech.locatech.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final VeiculoRepository veiculoRepository;

    public AluguelService(AluguelRepository aluguelRepository, VeiculoRepository veiculoRepository) {
        this.aluguelRepository = aluguelRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public List<Aluguel> findAllAlugueis(int page, int size) {
        int offset = (page -1) * size;
        return aluguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findAluguelById(Long id){
        return Optional.ofNullable(this.aluguelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado")));
    }

    public void saveAluguel(AluguelRequestDTO dto){
        var aluguel = mapper(dto);
        var save = this.aluguelRepository.save(aluguel);
        Assert.state(save == 1, "Erro ao salvar aluguel de: " + aluguel.getPessoaNome());
    }

    public void updateAluguel(AluguelRequestDTO dto, Long id){
        var aluguel = mapper(dto);
        var update = this.aluguelRepository.update(aluguel, id);
        if (update == 0){
            throw new ResourceNotFoundException("Aluguel não encontrada, id: " + id);
        }
    }

    public void deleteAluguel(Long id){
        var delete = this.aluguelRepository.delete(id);
        if (delete == 0){
            throw new ResourceNotFoundException("Aluguel não encontrado, id: " + id);
        }
    }

    private BigDecimal getValorAluguel(AluguelRequestDTO dto){
        var veiculo = veiculoRepository.findById(dto.veiculoId()).orElseThrow(() -> new RuntimeException("Veiculo não encontrado"));
        var qtdDias = BigDecimal.valueOf(dto.dataFim().getDayOfYear() - dto.dataInicio().getDayOfYear());
        return qtdDias.multiply(veiculo.getValorDiaria());
    }

    private Aluguel mapper(AluguelRequestDTO dto) {
        return new Aluguel(dto, getValorAluguel(dto));
    }
}
