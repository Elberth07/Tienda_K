package com.tienda.controller;

import com.tienda.domain.Item;
import com.tienda.domain.Producto;
import com.tienda.service.FirebaseStorageService;
import com.tienda.service.ItemService;
import com.tienda.service.ProductoService;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CarritoController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ProductoService productoService;

    @GetMapping("/carrito/listado")
    public String listado(Model model) {
        var lista = itemService.getItems();

        model.addAttribute("items", lista);
        model.addAttribute("carritoTotal", itemService.getTotal());

        return "/categoria/listado";
    }

    @PostMapping("/carrito/guardar")
    public String guardar(Item item) {
itemService.update(item);
        
        return "redirect:/carrito/listado";

    }

    @GetMapping("/carrito/eliminar/idProducto")
    public String eliminar(Item item) {
itemService.delete(item);
        return "redirect:/carrito/listado";

    }

    @GetMapping("/carrito/modificar/{idProducto}")
    public String modificar(Item item, Model model) {
        item= itemService.getItem(item);
        model.addAttribute("item",item);

        return "/carrito/modifica";

    }

    @GetMapping("/carrito/agregar/{idProducto}")
    public ModelAndView agregar(Item item, Model model) {
        Item item2 = itemService.getItem(item);
        if (item2 == null){
        Producto producto = productoService.getProducto(item);
        item2 = new Item(producto);           
        }
        itemService.save(item2);
    var lista = itemService.getItems();
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", lista.size());
        model.addAttribute("carritoTotal",itemService.getTotal() );


        return new ModelAndView("/carrito/fragmentos :: verCarritos");

    }

    @GetMapping("/facturar/carrito")
    public String facturar(){
        itemService.facturar();
        return "redirect:/";
    }
    
}
