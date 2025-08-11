package com.tienda.service;

import com.tienda.domain.Rol;
import com.tienda.repository.RollRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RollService {

    @Autowired
    private RollRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<Rol> getRolls( ) {
        var lista = categoriaRepository.findAll();
        return lista;
    }
    @Transactional(readOnly = true)
    public Rol getRoll(Rol role) {
        return categoriaRepository.findById(role.getIdRol()).orElse(null);
    }

    @Transactional
    public void save(Rol categoria) {
        categoriaRepository.save(categoria);
    }

    @Transactional
    public boolean delete(Rol categoria) {
        try {
             categoriaRepository.delete(categoria);
        } catch (Exception e) {
        } return false;
    }
}
