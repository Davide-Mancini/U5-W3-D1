package davidemancini.U5_W3_D1.services;

import davidemancini.U5_W3_D1.entities.Dipendente;
import davidemancini.U5_W3_D1.exceptions.UnauthotizedException;
import davidemancini.U5_W3_D1.payloads.LoginDTO;
import davidemancini.U5_W3_D1.security.JWTTools;
import davidemancini.U5_W3_D1.entities.Dipendente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bCrypt;

    public String checkCredentialAndNewToken(LoginDTO body){
        davidemancini.U5_W3_D1.entities.Dipendente dipendenteTrovato = dipendenteService.findByEmail(body.email());
        if (bCrypt.matches(body.password(),dipendenteTrovato.getPassword())){
            return jwtTools.createToken(dipendenteTrovato);

        }else{
            throw new UnauthotizedException("Credenzialio errate");
        }
    }


}
