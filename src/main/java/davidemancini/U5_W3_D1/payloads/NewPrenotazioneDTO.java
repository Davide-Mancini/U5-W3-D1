package davidemancini.U5_W3_D1.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record NewPrenotazioneDTO(
        @NotNull(message = "Devi inserire una data")
        LocalDate data_richiesta,
//        @NotBlank
        @Size(min = 1,max = 35)
        String note,
        @NotNull(message = "Assegna un dipendente")
        UUID dipendente,
        @NotNull(message = "Assegna un viaggio")
        UUID viaggio
) {
}
