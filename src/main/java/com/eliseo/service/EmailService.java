package com.eliseo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private static final String BREVO_URL = "https://api.brevo.com/v3/smtp/email";

    private final String apiKey;
    private final String fromAddress;
    private final String css;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public EmailService(
            @Value("${brevo.api.key:}") String apiKey,
            @Value("${brevo.from.email:}") String fromAddress,
            ObjectMapper objectMapper) throws IOException {
        this.apiKey = apiKey;
        this.fromAddress = fromAddress;
        this.css = readResource("email/email.css");
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = objectMapper;
    }

    public void inviaConfermaPrenotazione(String to, Map<String, String> vars) {
        send(to, "Prenotazione confermata — Eliseo", "email/conferma-prenotazione.html", vars);
    }

    public void inviaConfermaAbbonamento(String to, Map<String, String> vars) {
        send(to, "Abbonamento attivato — Eliseo", "email/conferma-abbonamento.html", vars);
    }

    private void send(String to, String subject, String templatePath, Map<String, String> vars) {
        if (apiKey == null || apiKey.isBlank()) {
            log.info("[EMAIL-MOCK] to={} subject={} vars={}", to, subject, vars);
            return;
        }
        try {
            String html = buildBody(templatePath, vars);
            String body = objectMapper.writeValueAsString(Map.of(
                    "sender", Map.of("name", "Cinema Teatro Eliseo", "email", fromAddress),
                    "to", List.of(Map.of("email", to)),
                    "subject", subject,
                    "htmlContent", html
            ));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BREVO_URL))
                    .header("api-key", apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 201) {
                log.info("[EMAIL] Inviata a {} — {}", to, subject);
            } else {
                log.error("[EMAIL] Errore API Brevo ({}) — {}", response.statusCode(), response.body());
            }
        } catch (Exception e) {
            log.error("[EMAIL] Errore invio a {}: {}", to, e.getMessage());
        }
    }

    private String buildBody(String templatePath, Map<String, String> vars) throws IOException {
        String html = readResource(templatePath).replace("{styles}", css);
        for (var entry : vars.entrySet()) {
            html = html.replace("{" + entry.getKey() + "}", entry.getValue() != null ? entry.getValue() : "");
        }
        return html;
    }

    private String readResource(String path) throws IOException {
        return new ClassPathResource(path).getContentAsString(StandardCharsets.UTF_8);
    }
}
