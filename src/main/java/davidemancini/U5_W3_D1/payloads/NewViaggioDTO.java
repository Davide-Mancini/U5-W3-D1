package davidemancini.U5_W3_D1.payloads;

import davidemancini.U5_W3_D1.entities.Stato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewViaggioDTO(
        @NotBlank(message = "Destinazione obbligatoria")
        String destinazione,
        @NotNull(message = "Data viaggio obbligatoria")
        LocalDate data_viaggio,
        @NotNull(message = "Inserisci lo stato")
        Stato stato
) {
}
