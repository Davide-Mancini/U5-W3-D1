package davidemancini.U5_W3_D1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue
    //IMPEDISCO LA CREAZIONE DEL SETTER DI LOMBOK PER L'ID
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String avatar;
    private String password;

    //COSTRUTTORE

    public Dipendente(String username, String nome, String cognome, String email,String password) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.avatar = "https://ui-avatars.com/api/?name="+nome+"+"+cognome ;
        //METTO UN AVATAR DI DEFAULT CHE SARà MODIFICABILE.
        this.password=password;
    }
}
