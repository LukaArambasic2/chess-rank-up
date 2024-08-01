package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.form.LoginForm;
import hr.fer.tzk.rankup.form.RegisterForm;
import hr.fer.tzk.rankup.service.AuthService;
import hr.fer.tzk.rankup.service.VerificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final VerificationService verificationService;

    @Autowired
    public AuthController(AuthService authService, VerificationService verificationService) {
        this.authService = authService;
        this.verificationService = verificationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginForm login) {
        AbstractMap.SimpleEntry<HttpStatus, String> response = authService.login(login);
        return ResponseEntity.status(response.getKey()).body(response.getValue());
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterForm form) {
        AbstractMap.SimpleEntry<HttpStatus, String> response = authService.register(form);
        return ResponseEntity.status(response.getKey()).body(response.getValue());
    }

    @GetMapping("/verify")
    public ResponseEntity verifyUser(@Param("verificationCode") String verificationCode) {
        return verificationService.verify(verificationCode);
    }
}
