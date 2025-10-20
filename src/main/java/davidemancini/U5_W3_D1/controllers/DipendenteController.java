package davidemancini.U5_W3_D1.controllers;

import davidemancini.U5_W3_D1.entities.Dipendente;
import davidemancini.U5_W3_D1.exceptions.MyValidationException;
import davidemancini.U5_W3_D1.payloads.NewDipendenteDTO;
import davidemancini.U5_W3_D1.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


//GET http://localhost:3001/users
//POST http://localhost:3001/users (+ request body)
//GET http://localhost:3001/users/{userId}
//PUT http://localhost:3001/users/{userId} (+ request body)
//DELETE  http://localhost:3001/users/{userId}

@RestController
@RequestMapping("/employees")
public class DipendenteController {
@Autowired
private DipendenteService dipendenteService;


    //TUTTI I DIPENDENTI
    @GetMapping
    public Page<Dipendente> getDipendenti(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "nome") String sortBy){
        return dipendenteService.findAll(page,size,sortBy);
    }
    //RITORNA DIPENDENTE SPECIFICO TRAMITE ID
    @GetMapping("/{employeeId}")
    public Dipendente getDipendenteById(@PathVariable UUID employeeId){
        return dipendenteService.findById(employeeId);
    }
    //ELIMINA UN DIPENDENTE
    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente (@PathVariable UUID employeeId){
        dipendenteService.findByIdAndDelete(employeeId);
    }
    //AGGIORNA DIPENDENTE
    @PutMapping("/{employeeId}")
    public Dipendente findByIdAndUpdate(@PathVariable UUID employeeId, @RequestBody @Validated NewDipendenteDTO body,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new MyValidationException(bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
       return dipendenteService.findByIdAndUpdate(employeeId,body);
    }
    //AGGIUNGI IMMAGINE
    @PatchMapping("/{employeeId}/avatar")
    public Dipendente uploadImage(@PathVariable UUID employeeId, @RequestParam("avatar")MultipartFile file){
        return dipendenteService.uploadAvatar(employeeId,file);
    }

}
