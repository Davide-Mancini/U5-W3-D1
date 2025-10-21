package davidemancini.U5_W3_D1.security;

import davidemancini.U5_W3_D1.entities.Dipendente;
import davidemancini.U5_W3_D1.exceptions.UnauthotizedException;
import davidemancini.U5_W3_D1.services.DipendenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private DipendenteService dipendenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader==null || !authHeader.startsWith("Bearer")){
            throw new UnauthotizedException("Formato del token errato");
        }
        String accessToken = authHeader.replace("Bearer ", "");

        //Questo verifica se il token è valido(non modificato o scaduto)
        jwtTools.verifyToken(accessToken);

        //Recupero id dal token(con il metodo creato nei tools)
        UUID id =jwtTools.extractIdFromToken(accessToken);

        //Ricerco il dipenente con l'id recuperato dal token
        Dipendente trovato = dipendenteService.findById(id);

        Authentication authentication = new UsernamePasswordAuthenticationToken(trovato,null,trovato.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return new AntPathMatcher().match("/auth/**",request.getServletPath());

    }
}
