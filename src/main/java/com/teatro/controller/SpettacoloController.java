package com.teatro.controller;

import com.teatro.dto.SpettacoloDto;
import com.teatro.dto.SpettacoloRequest;
import com.teatro.model.Spettacolo;
import com.teatro.service.SpettacoloService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spettacoli")
public class SpettacoloController {

    private final SpettacoloService service;

    public SpettacoloController(SpettacoloService service) {
        this.service = service;
    }

    @GetMapping
    public List<SpettacoloDto> list(@RequestParam(required = false) Spettacolo.Categoria categoria) {
        return service.list(categoria);
    }

    @GetMapping("/{id}")
    public SpettacoloDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public ResponseEntity<SpettacoloDto> create(@Valid @RequestBody SpettacoloRequest req) {
        return ResponseEntity.ok(service.create(req));
    }
}
