package davidemancini.U5_W3_D1.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorListDTO(
        String message,
        LocalDateTime oraErrore,
        List<String>errorsList
) {
}
