package com.teatro.repository;

import com.teatro.model.Posto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostoRepository extends JpaRepository<Posto, Long> {
    List<Posto> findBySalaId(Long salaId);
}
