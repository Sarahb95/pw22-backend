package com.teatro.repository;

import com.teatro.model.Abbonamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AbbonamentoRepository extends JpaRepository<Abbonamento, Long> {
    List<Abbonamento> findByUtenteId(Long utenteId);
    List<Abbonamento> findByUtenteIdAndDataFineGreaterThanEqual(Long utenteId, LocalDate oggi);
}
