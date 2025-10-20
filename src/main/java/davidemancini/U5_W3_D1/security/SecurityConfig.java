package davidemancini.U5_W3_D1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //NON UNA CLASSE CONFIG NORMALE, MA UNA CLASSE CONFIG SPECIALE PER SPRING SECURITY
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //CONFIGURAZIONE SICUREZZA SPRING USANDO UNA CATENA DI FILTRAGGIO PER PERSONALIZZARE.
        httpSecurity.formLogin(formLogin-> formLogin.disable()); //DISABILITO IL FORM DI LOGIN DI DEFAULT
        httpSecurity.csrf(csrf-> csrf.disable());
        httpSecurity.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests((request-> request.requestMatchers("/**").permitAll()));//PERMETTE TUTTE LE RICHIESTE
        return httpSecurity.build();

    }
}
