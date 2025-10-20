package davidemancini.U5_W3_D1.repositories;

import davidemancini.U5_W3_D1.entities.Dipendente;
import davidemancini.U5_W3_D1.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    //QUERY DERIVATA CHE CERCA PER DIPENDENTE E VIAGGIO, CON _DATAVIAGGIO ACCEDO ALL'ATTRIBUTO DI VIAGGIO CHE è DI TIPO LOCALDATE
    boolean existsByDipendenteAndViaggio_DataViaggio(Dipendente dipendente, LocalDate dataViaggio);
}
