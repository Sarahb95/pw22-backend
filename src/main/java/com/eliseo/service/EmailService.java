package com.eliseo.service;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;
    private final String fromAddress;
    private final String css;

    // JavaMailSender è opzionale: se SMTP non è configurato si usa il mock via log
    public EmailService(
            @Autowired(required = false) JavaMailSender mailSender,
            @Value("${spring.mail.username:}") String fromAddress) throws IOException {
        this.mailSender = mailSender;
        this.fromAddress = fromAddress;
        this.css = readResource("email/email.css");
    }

    public void inviaConfermaPrenotazione(String to, Map<String, String> vars) {
        send(to, "Prenotazione confermata — Eliseo", "email/conferma-prenotazione.html", vars);
    }

    public void inviaConfermaAbbonamento(String to, Map<String, String> vars) {
        send(to, "Abbonamento attivato — Eliseo", "email/conferma-abbonamento.html", vars);
    }

    private void send(String to, String subject, String templatePath, Map<String, String> vars) {
        if (mailSender == null || fromAddress == null || fromAddress.isBlank()) {
            log.info("[EMAIL-MOCK] to={} subject={} vars={}", to, subject, vars);
            return;
        }
        try {
            String html = buildBody(templatePath, vars);
            MimeMessage msg = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(msg, false, "UTF-8");
            helper.setFrom(fromAddress, "Cinema Teatro Eliseo");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);
            mailSender.send(msg);
            log.info("[EMAIL] Inviata a {} — {}", to, subject);
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
