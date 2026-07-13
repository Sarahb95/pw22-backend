package com.teatro.controller;

import com.teatro.dto.PostoStatoDto;
import com.teatro.dto.ReplicaDto;
import com.teatro.service.ReplicaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repliche")
public class ReplicaController {

    private final ReplicaService replicaService;

    public ReplicaController(ReplicaService replicaService) {
        this.replicaService = replicaService;
    }

    @GetMapping
    public List<ReplicaDto> list(@RequestParam(required = false) Long spettacoloId) {
        return replicaService.list(spettacoloId);
    }

    @GetMapping("/{id}")
    public ReplicaDto get(@PathVariable Long id) {
        return replicaService.get(id);
    }

    @GetMapping("/{id}/posti")
    public List<PostoStatoDto> posti(@PathVariable Long id) {
        return replicaService.piantaPosti(id);
    }
}
