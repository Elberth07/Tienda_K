package com.tienda.controller;

import com.tienda.domain.Categoria;
import com.tienda.domain.Rol;
import com.tienda.domain.Role;
import com.tienda.domain.Usuario;
import com.tienda.service.CategoriaService;
import com.tienda.service.FirebaseStorageService;
import com.tienda.service.RoleService;
import com.tienda.service.RollService;
import com.tienda.service.UsuarioService;
import java.util.ArrayList;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario_role")
public class UsuarioRoleController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RollService rollService;

    @GetMapping("/asignar")
    public String asignar(Usuario usuario, Model model) {
        if (usuario == null) {
            usuario = new Usuario();
        }
        usuario = usuarioService.getUsuarioPorUsername(usuario.getUsername());
        if (usuario != null) {
            model.addAttribute("usuario", usuario);

            var lista = roleService.getRoles();
            ArrayList<String> roleDisponible = new ArrayList<>();
            for(Role r : lista){
                roleDisponible.add(r.getRol());
            }
            var roleAsignados = usuario.getRoles();
            for (Rol r : roleAsignados){
                    roleDisponible.remove(r.getNombre());
            }
            model.addAttribute("rolesDisponibles", roleDisponible);
            model.addAttribute("rolesAsignados", roleAsignados);
            model.addAttribute("idUsuario", usuario.getIdUsuario());
            model.addAttribute("username", usuario.getUsername());
            
        }
        return "/usuario_role/asignar";
    }
        @GetMapping("/agregar")
    public String agregar(Usuario usuario, Rol rol, Model model) {
        rollService.save(rol);
        return "redirect:/usuario_role/asignar?username="+usuario.getUsername();
        
        }
        @GetMapping("/eliminar")
    public String eliminar(Usuario usuario, Rol rol, Model model) {
        rollService.save(rol);
       // model.addAttribute("usuario", usuario);
        return "redirect:/usuario_rol/asignar?username="+usuario.getUsername();
        
    }
}
