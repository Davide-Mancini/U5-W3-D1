package davidemancini.U5_W3_D1.services;

import davidemancini.U5_W3_D1.entities.Viaggio;
import davidemancini.U5_W3_D1.exceptions.NotFoundException;
import davidemancini.U5_W3_D1.payloads.NewStatoViaggioDTO;
import davidemancini.U5_W3_D1.payloads.NewViaggioDTO;
import davidemancini.U5_W3_D1.repositories.ViaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ViaggioService {
    @Autowired
    private ViaggiRepository viaggiRepository;

    //SALVATAGGIO NUOVO VIAGGIO
    public Viaggio save(NewViaggioDTO body){
        Viaggio newViaggio = new Viaggio(body.destinazione(),body.data_viaggio(),body.stato());
        return viaggiRepository.save(newViaggio);
    }

    //TORNA TUTTI I VIAGGI CON PAGINAZIONE
    public Page<Viaggio> findAll(int pageNumber, int pageSize, String pageSortBy){
        if(pageSize>30) pageSize=30;
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(pageSortBy));
        return viaggiRepository.findAll(pageable);
    }

    //RICERCA TRAMITE ID
    public Viaggio findById(UUID id){
        return viaggiRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    //RICERCA TRAMITE ID E UPDATE
    public Viaggio findByIdAndUpdate(UUID id, NewViaggioDTO body){
        Viaggio trovato = findById(id);
        trovato.setDataViaggio(body.data_viaggio());
        trovato.setDestinazione(body.destinazione());
        Viaggio viaggioModificato = viaggiRepository.save(trovato);
        return viaggioModificato;
    }

    //RIERCA TRAMITE ID E DELETE
    public void findByIdAndDelete(UUID id){
        Viaggio trovato = findById(id);
        viaggiRepository.delete(trovato);
    }
    //MODIFICA DELLO STATO DEL VIAGGIO
    public Viaggio findBYIdAndUpdateStato(UUID id, NewStatoViaggioDTO body){
        Viaggio viaggioTrovato = findById(id);
        viaggioTrovato.setStato(body.stato());
        Viaggio viaggioModificato = viaggiRepository.save(viaggioTrovato);
        return viaggioModificato;

    }
}
