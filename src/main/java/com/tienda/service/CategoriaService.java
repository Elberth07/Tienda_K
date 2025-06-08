package com.tienda.service;

import com.tienda.domain.Categoria;
import com.tienda.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class CategoriaService {
    //Anotacion para crear una unica instancia de respository y se crea automaticamente
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Transactional(readOnly=true)
    public List<Categoria> getCategorias (boolean activo){
        var lista= categoriaRepository.findAll();
        if (activo) {
            lista.removeIf(e-> !e.isActivo());
        }
        return lista;
    }
}
