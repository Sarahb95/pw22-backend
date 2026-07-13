package com.teatro.service;

import com.teatro.dto.SpettacoloDto;
import com.teatro.dto.SpettacoloRequest;
import com.teatro.model.Spettacolo;
import com.teatro.repository.SpettacoloRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public SpettacoloDto create(SpettacoloRequest req) {
        Spettacolo s = Spettacolo.builder()
                .titolo(req.titolo())
                .categoria(req.categoria())
                .regista(req.regista())
                .castPrincipale(req.castPrincipale())
                .descrizione(req.descrizione())
                .trama(req.trama())
                .genere(req.genere())
                .durataMin(req.durataMin())
                .locandinaUrl(req.locandinaUrl())
                .build();
        return SpettacoloDto.from(repo.save(s));
    }
}
