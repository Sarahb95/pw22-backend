package com.teatro.config;

import com.teatro.repository.UtenteRepository;
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
            "admin@eliseo.it",  "admin123",
            "mario@example.it", "mario123",
            "lucia@example.it", "lucia123"
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
