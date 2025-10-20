package davidemancini.U5_W3_D1.services;

import davidemancini.U5_W3_D1.entities.Dipendente;
import davidemancini.U5_W3_D1.entities.Prenotazione;
import davidemancini.U5_W3_D1.entities.Viaggio;
import davidemancini.U5_W3_D1.exceptions.MyBadRequestException;
import davidemancini.U5_W3_D1.exceptions.NotFoundException;
import davidemancini.U5_W3_D1.payloads.NewPrenotazioneDTO;
import davidemancini.U5_W3_D1.repositories.DipendenteRepository;
import davidemancini.U5_W3_D1.repositories.PrenotazioneRepository;
import davidemancini.U5_W3_D1.repositories.ViaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private ViaggiRepository viaggiRepository;

    //SALVATAGGIO NUOVA PRENOTAZIONE
    public Prenotazione save(NewPrenotazioneDTO body) {
        Dipendente dipendenteTrovato = dipendenteRepository.findById(body.dipendente()).orElseThrow(()-> new NotFoundException(body.dipendente()));
        Viaggio viaggioTrovato = viaggiRepository.findById(body.viaggio()).orElseThrow(()-> new NotFoundException(body.viaggio()));
        Prenotazione newPrenotazione = new Prenotazione(body.data_richiesta(), body.note(), dipendenteTrovato,viaggioTrovato);

        //CONTROLLO SULLA DATA DEL NUOVO VIAGGIO
        boolean prenotazioneStessaData= prenotazioneRepository.existsByDipendenteAndViaggio_DataViaggio(dipendenteTrovato,viaggioTrovato.getDataViaggio());
        if (prenotazioneStessaData){
            throw new MyBadRequestException("Non puoi prenotare due viaggi con la stessa data");
        }
        return prenotazioneRepository.save(newPrenotazione);
    }

    //TORNA TUTTE LE PRENOTAZIONE CON PAGINAZIONE
    public Page<Prenotazione> findAll(int pageNumber, int pageSize, String pageSortBy){
        if(pageSize>30) pageSize=30;
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(pageSortBy));
        return prenotazioneRepository.findAll(pageable);
    }

    //RICERCA TRAMITE ID
    public Prenotazione findById (UUID id){
        return prenotazioneRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    //RICERCA TRAMITE ID E UPDATE
    public Prenotazione findByIdAndUpdate(UUID id, NewPrenotazioneDTO body){
        Dipendente dipendenteTrovato = dipendenteRepository.findById(body.dipendente()).orElseThrow(()-> new NotFoundException(body.dipendente()));
        Viaggio viaggioTrovato = viaggiRepository.findById(body.viaggio()).orElseThrow(()-> new NotFoundException(body.viaggio()));
        Prenotazione trovata = findById(id);
        trovata.setData_richiesta(body.data_richiesta());
        trovata.setNote(body.note());
        trovata.setDipendente(dipendenteTrovato);
        trovata.setViaggio(viaggioTrovato);
        Prenotazione prenotazioneModificata = prenotazioneRepository.save(trovata);
        return prenotazioneModificata;
    }

    //RIERCA TRAMITE ID E DELETE
    public void findByIdAndDelete(UUID id){
        Prenotazione trovata = findById(id);
        prenotazioneRepository.delete(trovata);
    }

}
