package davidemancini.U5_W3_D1.controllers;


import davidemancini.U5_W3_D1.entities.Dipendente;
import davidemancini.U5_W3_D1.exceptions.MyValidationException;
import davidemancini.U5_W3_D1.payloads.LoginDTO;
import davidemancini.U5_W3_D1.payloads.LoginResponseDTO;
import davidemancini.U5_W3_D1.payloads.NewDipendenteDTO;
import davidemancini.U5_W3_D1.services.AuthorizationService;
import davidemancini.U5_W3_D1.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    @Autowired
    AuthorizationService authorizationService;
    @Autowired
    DipendenteService dipendenteService;

    @PostMapping("/login")
    public LoginResponseDTO login (@RequestBody LoginDTO body){
        return new LoginResponseDTO(authorizationService.checkCredentialAndNewToken(body));
    }
    //SALVA UN DIPENDENTE (STATUS CODE 201)
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated NewDipendenteDTO body, BindingResult bindingResult){
        //bindinresult MI PERMETTE DI GESTIRE GLI ERRORI PIU FACILMENTE
        if (bindingResult.hasErrors()){
            throw new MyValidationException(bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return dipendenteService.save(body);

    }
}
