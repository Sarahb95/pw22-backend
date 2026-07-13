package com.teatro.controller;

import com.teatro.dto.AbbonamentoCopertura;
import com.teatro.dto.AbbonamentoDto;
import com.teatro.dto.AbbonamentoRequest;
import com.teatro.service.AbbonamentoService;
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
