package davidemancini.U5_W3_D1.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import davidemancini.U5_W3_D1.entities.Dipendente;
import davidemancini.U5_W3_D1.exceptions.NotFoundException;
import davidemancini.U5_W3_D1.payloads.NewDipendenteDTO;
import davidemancini.U5_W3_D1.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private ViaggioService viaggioService;
    @Autowired
    private Cloudinary cloudinaryUploader;


    //SALVA NUOVO DIPENDENTE
    public Dipendente save(NewDipendenteDTO body){
        Dipendente newDipendente = new Dipendente(body.username(),body.nome(), body.cognome(), body.email(),body.password());
        return dipendenteRepository.save(newDipendente);
    }

    //TROVA TUTTI I DIPENDENTI CON PAGINAZIONE
    public Page<Dipendente> findAll(int pageNumber, int pageSize,String pageSortBy){
        //CONTROLLO SUL NUMERO DI ELEMENTI PER PAGINA, NON PUO ESSERE PIU DI 30
        if(pageSize>30) pageSize=30;
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(pageSortBy));
        return dipendenteRepository.findAll(pageable);
    }

    //RICERCA TRAMITE ID
    public Dipendente findById (UUID id){
        return dipendenteRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    //RICERCA TRAMITE ID E UPDATE
    public Dipendente findByIdAndUpdate(UUID id, NewDipendenteDTO body){
        Dipendente trovato = findById(id);
        if (!trovato.getEmail().equals(body.email())){

            dipendenteRepository.findByEmail(body.email());
            };
        trovato.setNome(body.nome());
        trovato.setCognome(body.cognome());
        trovato.setUsername(body.username());
        trovato.setEmail(body.email());
        Dipendente dipendenteModificato = dipendenteRepository.save(trovato);
        return dipendenteModificato;
        }

    //RICERCA TRAMITE ID E DELETE
    public void findByIdAndDelete(UUID id){
        Dipendente trovato = findById(id);
        dipendenteRepository.delete(trovato);
    }

    //INSERIMENTO DI UNA NUOVA IMMAGINE COME AVATAR
    public Dipendente uploadAvatar(UUID id, MultipartFile file){
        Dipendente dipendenteTrovato = findById(id);
        try {
            Map result = cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imgURL = (String) result.get("url");
            dipendenteTrovato.setAvatar(imgURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dipendenteRepository.save(dipendenteTrovato);
    }

    public Dipendente findByEmail (String email){

           return  dipendenteRepository.findByEmail(email);

    }

    }

