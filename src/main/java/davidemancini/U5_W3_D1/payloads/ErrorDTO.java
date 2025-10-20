package davidemancini.U5_W3_D1.payloads;

import java.time.LocalDateTime;

public record ErrorDTO(
        String message,
        LocalDateTime oraErrore
) {
}
