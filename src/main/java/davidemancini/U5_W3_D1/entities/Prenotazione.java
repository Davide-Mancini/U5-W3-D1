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
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue
    //IMPEDISCO LA CREAZIONE DEL SETTER DI LOMBOK PER L'ID
    @Setter(AccessLevel.NONE)
    private UUID id;
    private LocalDate data_richiesta;
    private String note;
    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;
    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;


    //COSTRUTTORE
    public Prenotazione(LocalDate data_richiesta, String note, Dipendente dipendente, Viaggio viaggio) {
        this.data_richiesta = data_richiesta;
        this.note = note;
        this.dipendente = dipendente;
        this.viaggio = viaggio;
    }
}
