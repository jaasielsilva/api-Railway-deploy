package com.api.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.api.app.model.Pessoa;

@Repository
public interface Repositorio extends CrudRepository<Pessoa, Integer>{

    @SuppressWarnings("null")
    List<Pessoa>findAll();

    Pessoa findById(int id);
    // Novo método para contar o número total de pessoas
    long count(); // Método para contar as pessoas
    
}
