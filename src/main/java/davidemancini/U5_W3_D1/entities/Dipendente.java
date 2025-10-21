package davidemancini.U5_W3_D1.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "dipendenti")
@JsonIgnoreProperties({"password","authorities","enabled","accountNonLocked","accountNonExpired","credentialsNonExpired"})
public class Dipendente implements UserDetails {
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
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    //COSTRUTTORE

    public Dipendente(String username, String nome, String cognome, String email,String password) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.avatar = "https://ui-avatars.com/api/?name="+nome+"+"+cognome ;
        //METTO UN AVATAR DI DEFAULT CHE SARà MODIFICABILE.
        this.password=password;
        //DI DEFAULT SARà UN UTENTE NORMALE
        this.ruolo=Ruolo.UTENTE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruolo.name()));
    }
    @Override
    public String getUsername() {
        return email;
    }
}
