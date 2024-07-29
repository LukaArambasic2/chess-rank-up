package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.MemberLoginDto;
import hr.fer.tzk.rankup.dto.MemberRegisterDto;
import hr.fer.tzk.rankup.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberLoginDto memberLogin) {
        AbstractMap.SimpleEntry<HttpStatus, String> response = authService.login(memberLogin);
        return ResponseEntity.status(response.getKey()).body(response.getValue());
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody MemberRegisterDto memberRegister) {
        AbstractMap.SimpleEntry<HttpStatus, String> response = authService.register(memberRegister);
        return ResponseEntity.status(response.getKey()).body(response.getValue());
    }
}
