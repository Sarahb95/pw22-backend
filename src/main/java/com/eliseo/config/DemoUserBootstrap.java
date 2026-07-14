package com.eliseo.config;

import com.eliseo.repository.UtenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Al boot riallinea le password degli utenti demo del seed (che contiene
 * hash placeholder) con BCrypt veri, così è possibile effettuare il login
 * con credenziali note. Operazione idempotente.
 */
@Component
public class DemoUserBootstrap implements CommandLineRunner {

    private static final Map<String, String> DEMO_CREDENTIALS = Map.of(
            "marco@example.it",  "marco123",
            "giulia@example.it", "giulia123"
    );

    private final UtenteRepository utenteRepo;
    private final PasswordEncoder encoder;

    public DemoUserBootstrap(UtenteRepository utenteRepo, PasswordEncoder encoder) {
        this.utenteRepo = utenteRepo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        DEMO_CREDENTIALS.forEach((email, plain) ->
            utenteRepo.findByEmail(email).ifPresent(u -> {
                if (!encoder.matches(plain, u.getPasswordHash())) {
                    u.setPasswordHash(encoder.encode(plain));
                    utenteRepo.save(u);
                }
            })
        );
    }
}
