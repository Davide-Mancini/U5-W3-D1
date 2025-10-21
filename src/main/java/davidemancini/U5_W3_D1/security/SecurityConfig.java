package davidemancini.U5_W3_D1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity //NON UNA CLASSE CONFIG NORMALE, MA UNA CLASSE CONFIG SPECIALE PER SPRING SECURITY
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //CONFIGURAZIONE SICUREZZA SPRING USANDO UNA CATENA DI FILTRAGGIO PER PERSONALIZZARE.
        httpSecurity.formLogin(formLogin-> formLogin.disable()); //DISABILITO IL FORM DI LOGIN DI DEFAULT
        httpSecurity.csrf(csrf-> csrf.disable());
        httpSecurity.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests((request-> request.requestMatchers("/**").permitAll()));//PERMETTE TUTTE LE RICHIESTE
        httpSecurity.cors(Customizer.withDefaults()); //CONFIGURAZIONEN CORS
        return httpSecurity.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    //BEAN PER IL CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
