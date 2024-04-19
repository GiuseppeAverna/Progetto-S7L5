package Progetto.S7L5.security;

import Progetto.S7L5.entities.User;
import Progetto.S7L5.services.UserService;
import Progetto.S7L5.exceptions.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Estrai il token JWT dall'header Authorization
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Per favore inserisci il token nell'Authorization Header");
        }
        String accessToken = authHeader.substring(7);

        // Verifica e ottieni l'ID dell'utente dal token JWT
        String id = jwtTools.extractIdFromToken(accessToken);

        // Carica l'utente dal servizio utenti
        User currentUser = userService.findById(Long.parseLong(id));

        // Autenticazione dell'utente
        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null);

        // Imposta l'autenticazione nel contesto di sicurezza
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Prosegui con il filtro nella catena
        filterChain.doFilter(request, response);
    }
}



