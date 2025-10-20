package davidemancini.U5_W3_D1.repositories;

import davidemancini.U5_W3_D1.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, UUID> {
    //QUERY DERIVATA CHE RICERCA PER EMIAL
    Dipendente findByEmail(String email);
}
