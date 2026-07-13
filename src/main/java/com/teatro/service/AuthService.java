package com.teatro.service;

import com.teatro.dto.LoginRequest;
import com.teatro.dto.RegisterRequest;
import com.teatro.model.Utente;
import com.teatro.repository.UtenteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UtenteRepository utenteRepo;
    private final PasswordEncoder encoder;

    public AuthService(UtenteRepository utenteRepo, PasswordEncoder encoder) {
        this.utenteRepo = utenteRepo;
        this.encoder = encoder;
    }

    @Transactional
    public Utente register(RegisterRequest req) {
        if (utenteRepo.existsByEmail(req.email())) {
            throw new IllegalStateException("Esiste già un account con questa email");
        }
        Utente u = Utente.builder()
                .email(req.email())
                .passwordHash(encoder.encode(req.password()))
                .nome(req.nome())
                .cognome(req.cognome())
                .dataNascita(req.dataNascita())
                .ruolo(Utente.Ruolo.CLIENTE)
                .build();
        return utenteRepo.save(u);
    }

    @Transactional(readOnly = true)
    public Utente login(LoginRequest req) {
        Utente u = utenteRepo.findByEmail(req.email())
                .orElseThrow(() -> new IllegalArgumentException("Credenziali non valide"));
        if (!encoder.matches(req.password(), u.getPasswordHash())) {
            throw new IllegalArgumentException("Credenziali non valide");
        }
        return u;
    }
}
