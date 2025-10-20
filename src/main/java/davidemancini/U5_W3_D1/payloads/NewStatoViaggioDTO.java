package davidemancini.U5_W3_D1.payloads;

import davidemancini.U5_W3_D1.entities.Stato;
import jakarta.validation.constraints.NotNull;

public record NewStatoViaggioDTO(
        @NotNull(message = "Definisci il nuovo stato")
        Stato stato
) {
}
