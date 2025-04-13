package com.analab.locatech.locatech.repositories;

import com.analab.locatech.locatech.entities.Aluguel;

import java.util.List;
import java.util.Optional;

public interface AluguelRepository {
    Optional<Aluguel> findById(Long id);
    List<Aluguel> findAll(int size, int offset);
    Integer save(Aluguel alguel);
    Integer update(Aluguel aluguel, Long id);
    Integer delete(Long id);
}
