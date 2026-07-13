package com.teatro.repository;

import com.teatro.model.Spettacolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpettacoloRepository extends JpaRepository<Spettacolo, Long> {
    List<Spettacolo> findByCategoria(Spettacolo.Categoria categoria);
}
