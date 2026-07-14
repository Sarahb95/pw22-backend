package com.eliseo.controller;

import com.eliseo.dto.AbbonamentoCopertura;
import com.eliseo.dto.AbbonamentoDto;
import com.eliseo.dto.AbbonamentoRequest;
import com.eliseo.service.AbbonamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abbonamenti")
public class AbbonamentoController {

    private final AbbonamentoService service;

    public AbbonamentoController(AbbonamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AbbonamentoDto> acquista(@Valid @RequestBody AbbonamentoRequest req) {
        return ResponseEntity.ok(service.acquista(req));
    }

    @GetMapping
    public List<AbbonamentoDto> listByUtente(@RequestParam Long utenteId) {
        return service.listByUtente(utenteId);
    }

    @GetMapping("/copertura")
    public AbbonamentoCopertura copertura(@RequestParam Long utenteId) {
        return service.verificaCopertura(utenteId);
    }
}
