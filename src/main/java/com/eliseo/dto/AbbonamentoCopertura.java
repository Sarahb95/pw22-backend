package com.eliseo.dto;

/**
 * Esito del controllo di copertura dell'abbonamento per un utente.
 *
 * @param coperta         true se l'utente ha un abbonamento attivo che copre la prenotazione
 * @param tipo            tipo di abbonamento attivo (es. "STAGIONALE"), null se non coperto
 * @param ingressiResidui -1 = illimitati (STAGIONALE/UNDER25), N = ingressi residui (OPEN10)
 */
public record AbbonamentoCopertura(
        boolean coperta,
        String  tipo,
        int     ingressiResidui,
        int     maxPostiPerEntrata
) {
    public static AbbonamentoCopertura notCovered() {
        return new AbbonamentoCopertura(false, null, 0, 0);
    }
}
