package hr.fer.tzk.rankup.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String email;
            try {
                email = jwtUtil.validateAndExtractEmail(token);
            } catch (JwtException ex) {
                // invalid signature, expired, etc.
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            if (email == null) {
                // validation method returned null for some reason
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // 1) Build an Authentication object
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            email,                     // principal (the email)
                            null,                      // credentials (we don't need it here)
                            Collections.emptyList()    // authorities (empty; add roles here if you like)
                    );

            // 2) Store it in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(auth);

            // 3) (Optional) also make the email available as a request attribute
            request.setAttribute("email", email);
        }

        // 4) Always continue the chain
        filterChain.doFilter(request, response);
    }

}
