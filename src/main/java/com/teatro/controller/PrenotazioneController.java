package com.teatro.controller;

import com.teatro.dto.PrenotazioneBatchRequest;
import com.teatro.dto.PrenotazioneDto;
import com.teatro.service.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    private final PrenotazioneService service;

    public PrenotazioneController(PrenotazioneService service) {
        this.service = service;
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Long>> prenotaBatch(@Valid @RequestBody PrenotazioneBatchRequest req) {
        List<Long> ids = service.prenotaBatch(req).stream().map(PrenotazioneDto::id).toList();
        return ResponseEntity.ok(ids);
    }

    @GetMapping
    public List<PrenotazioneDto> listByUtente(@RequestParam Long utenteId) {
        return service.listByUtente(utenteId);
    }
}
