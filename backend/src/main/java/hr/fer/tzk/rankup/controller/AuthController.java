package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.MemberLoginDto;
import hr.fer.tzk.rankup.dto.MemberRegisterDto;
import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.security.PasswordHasher;
import hr.fer.tzk.rankup.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;
    private final PasswordHasher passwordHasher;

    @Autowired
    public AuthController(MemberService memberService, @Qualifier("argon2idHasher") PasswordHasher passwordHasher) {
        // TODO: When doing login, check which algorithm was used for original hashing and then hash it
        this.memberService = memberService;
        this.passwordHasher = passwordHasher;
    }

    private boolean isValidPassword(String password) {
        final String PASSWORD_REGEX = "^[A-Za-z0-9!?._-]+$";
        final int MIN_PASSWORD_LENGTH = 8;
        final int MAX_PASSWORD_LENGTH = 30;
        return password.length() >= MIN_PASSWORD_LENGTH && password.length() <= MAX_PASSWORD_LENGTH && password.matches(PASSWORD_REGEX);
    }

    private ResponseEntity<String> validateLoginDto(MemberLoginDto memberLogin) {
        if (memberLogin.getEmail() == null || memberLogin.getEmail().isBlank() ||
                memberLogin.getPassword() == null || memberLogin.getPassword().isBlank() ||
                !isValidPassword(memberLogin.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
        return null;
    }

    private ResponseEntity<String> validateRegisterDto(MemberRegisterDto memberRegister) {
        if (memberRegister.getFirstName() == null || memberRegister.getFirstName().isBlank()) {
            return ResponseEntity.badRequest().body("Missing first name");
        }

        if (memberRegister.getFirstName().length() > 30) {
            return ResponseEntity.badRequest().body("First name must be 30 characters or less");
        }

        if (memberRegister.getLastName() == null || memberRegister.getLastName().isBlank()) {
            return ResponseEntity.badRequest().body("Missing last name");
        }

        if (memberRegister.getLastName().length() > 30) {
            return ResponseEntity.badRequest().body("Last name must be 30 characters or less");
        }

        if (memberRegister.getJmbag() == null || memberRegister.getJmbag().isBlank()) {
            return ResponseEntity.badRequest().body("Missing JMBAG");
        }

        if (memberRegister.getEmail() == null || memberRegister.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("Missing email");
        }

        if (memberRegister.getPassword() == null || memberRegister.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body("Missing password");
        }

        if (memberRegister.getRepeatPassword() == null || memberRegister.getRepeatPassword().isBlank()) {
            return ResponseEntity.badRequest().body("Missing repeat password");
        }

        if (!memberRegister.getPassword().equals(memberRegister.getRepeatPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }

        if (!isValidPassword(memberRegister.getPassword())) {
            return ResponseEntity.badRequest().body("Password can contain only: uppercase and lowercase letters, numbers and special characters '.', '?', '!', '_' and '-'");
        }

        Optional<Member> memberByEmailOpt = memberService.findMemberByEmail(memberRegister.getEmail());
        if (memberByEmailOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
        }

        Optional<Member> memberByJmbagOpt = memberService.findMemberByJmbag(memberRegister.getJmbag());
        if (memberByJmbagOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("JMBAG already in use");
        }

        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberLoginDto memberLogin) {
        ResponseEntity<String> validationResponse = validateLoginDto(memberLogin);
        if (validationResponse != null) {
            return validationResponse;
        }

        Optional<Member> memberOpt = memberService.findMemberByEmail(memberLogin.getEmail());
        if (memberOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        Member member = memberOpt.get();
        String storedHash = member.getPasswordHash();
        String salt = member.getSalt();

        if (!passwordHasher.checkPassword(memberLogin.getPassword(), salt, storedHash)) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        if (!member.isVerified()) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        return ResponseEntity.ok(null);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody MemberRegisterDto memberRegister) {
        ResponseEntity<String> validationResponse = validateRegisterDto(memberRegister);
        if (validationResponse != null) {
            return validationResponse;
        }

        String salt = passwordHasher.generateSalt();
        String passwordHash = passwordHasher.hashPassword(memberRegister.getPassword(), salt);

        Member newMember;
        try {
            newMember = new Member(memberRegister.getFirstName(), memberRegister.getLastName(), memberRegister.getJmbag(), memberRegister.getEmail(), passwordHash, salt);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        memberService.createMember(newMember);
        return ResponseEntity.ok(null);
    }
}
