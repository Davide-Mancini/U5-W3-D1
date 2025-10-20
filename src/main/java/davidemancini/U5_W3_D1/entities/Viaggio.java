package davidemancini.U5_W3_D1.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "viaggi")
public class Viaggio {
    @Id
    @GeneratedValue
    //IMPEDISCO LA CREAZIONE DEL SETTER DI LOMBOK PER L'ID
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String destinazione;
    private LocalDate dataViaggio;
    @Enumerated(EnumType.STRING)
    private Stato stato;
    //COSTRUTTORE

    public Viaggio(String destinazione, LocalDate data_viaggio, Stato stato) {
        this.destinazione = destinazione;
        this.dataViaggio = data_viaggio;
        this.stato = stato;
    }
}
