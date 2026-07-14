package com.eliseo.controller;

import com.eliseo.dto.SpettacoloDto;
import com.eliseo.model.Spettacolo;
import com.eliseo.service.SpettacoloService;
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

}
