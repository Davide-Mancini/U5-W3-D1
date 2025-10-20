package davidemancini.U5_W3_D1.services;

import davidemancini.U5_W3_D1.entities.Dipendente;
import davidemancini.U5_W3_D1.exceptions.UnauthotizedException;
import davidemancini.U5_W3_D1.payloads.LoginDTO;
import davidemancini.U5_W3_D1.security.JWTTools;
import davidemancini.U5_W3_D1.entities.Dipendente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private JWTTools jwtTools;

    public String checkCredentialAndNewToken(LoginDTO body){
        davidemancini.U5_W3_D1.entities.Dipendente dipendenteTrovato = dipendenteService.findByEmail(body.email());
        if (dipendenteTrovato.getPassword().equals(body.password())){
            return jwtTools.createToken(dipendenteTrovato);

        }else{
            throw new UnauthotizedException("Credenzialio errate");
        }
    }


}
