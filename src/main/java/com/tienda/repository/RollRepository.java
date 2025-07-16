package com.tienda.repository;

import com.tienda.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RollRepository extends JpaRepository<Categoria, Long>{

}
