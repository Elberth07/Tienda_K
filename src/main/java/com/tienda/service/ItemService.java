package com.tienda.service;

import com.tienda.domain.Categoria;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    @Autowired
    private HttpSession session;

    public List<Categoria> getItems() {
        var lista = (List) session.getAttribute("listaItems");
        return lista;
    }

    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria Categoria) {
        return categoriaRepository.findById(Categoria.getIdCategoria()).orElse(null);
    }

    @Transactional
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Transactional
    public boolean delete(Categoria categoria) {
        try {
            categoriaRepository.delete(categoria);
        } catch (Exception e) {
        }
        return false;
    }
}
