package com.tienda.service;

import com.tienda.domain.Role;
import com.tienda.repository.RoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<Role> getRoles( ) {
        var lista = categoriaRepository.findAll();
        return lista;
    }
    @Transactional(readOnly = true)
    public Role getRole(Role role) {
        return categoriaRepository.findById(role.getRol()).orElse(null);
    }

    @Transactional
    public void save(Role categoria) {
        categoriaRepository.save(categoria);
    }

    @Transactional
    public boolean delete(Role categoria) {
        try {
             categoriaRepository.delete(categoria);
        } catch (Exception e) {
        } return false;
    }
}
