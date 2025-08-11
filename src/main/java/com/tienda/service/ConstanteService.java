package com.tienda.service;

import com.tienda.domain.Constante;
import com.tienda.repository.ConstanteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConstanteService {

    @Autowired
    private ConstanteRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<Constante> getConstantes() {
        var lista = categoriaRepository.findAll();
        return lista;
    }

    @Transactional(readOnly = true)
    public Constante getConstante(Constante Constante) {
        return categoriaRepository.findById(Constante.getIdConstante()).orElse(null);
    }

    @Transactional
    public void save(Constante categoria) {
        categoriaRepository.save(categoria);
    }

    @Transactional
    public boolean delete(Constante categoria) {
        try {
            categoriaRepository.delete(categoria);
        } catch (Exception e) {
        }
        return false;
    }
    @Transactional(readOnly = true)
    public Constante getConstantePorAtributo(String atributo) {
        return categoriaRepository.findByAtributo(atributo);
    }
}
