package com.tienda.service;

import com.tienda.domain.Categoria;
import com.tienda.domain.Item;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    @Autowired
    private HttpSession session;

    public List<Item> getItems() {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");
        return lista;
    }

    public Item geItem(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");
        if (lista != null) {
            for (Item i : lista) {
                if (item.getIdProducto() == i.getIdProducto()) {
                    return i;
                }
            }
        }
        return null;
    }

    public void save(Item item) {

        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");
        if (lista == null) {
            lista = new ArrayList<>();

        }
        var existe = false;
        for (Item i : lista) {
            if (item.getIdProducto() == i.getIdProducto()) {
                existe = true;
                if (i.getCantidad() < i.getExistencias()) {
                    i.setCantidad(i.getCantidad() + 1);
                }
                break;
            }
        }
        if (!existe) {
            item.setCantidad(1);
            lista.add(item);
        }
        session.setAttribute("listaItem", lista);
    }

    public void delete(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");
        if (lista != null) {
            var posicion = 1;
            var existe = false;
            for (Item i : lista) {
                posicion++;
                if (item.getIdProducto() == i.getIdProducto()) {
                    existe = true;
                    break;
                }
            }
            if (existe) {
                lista.remove(posicion);
                session.setAttribute("listaItem", lista);
            }
        }

    }
}
