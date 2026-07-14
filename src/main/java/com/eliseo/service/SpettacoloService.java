package com.eliseo.service;

import com.eliseo.dto.SpettacoloDto;
import com.eliseo.model.Spettacolo;
import com.eliseo.repository.SpettacoloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SpettacoloService {

    private final SpettacoloRepository repo;

    public SpettacoloService(SpettacoloRepository repo) {
        this.repo = repo;
    }

    public List<SpettacoloDto> list(Spettacolo.Categoria categoria) {
        var items = (categoria == null) ? repo.findAll() : repo.findByCategoria(categoria);
        return items.stream().map(SpettacoloDto::from).toList();
    }

    public SpettacoloDto get(Long id) {
        return SpettacoloDto.from(repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Spettacolo non trovato: " + id)));
    }
}
