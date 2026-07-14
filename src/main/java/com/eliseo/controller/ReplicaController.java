package com.eliseo.controller;

import com.eliseo.dto.PostoStatoDto;
import com.eliseo.dto.ReplicaDto;
import com.eliseo.service.ReplicaService;
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
