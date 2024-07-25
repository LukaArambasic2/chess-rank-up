package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.MemberLoginDto;
import hr.fer.tzk.rankup.dto.MemberRegisterDto;
import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private MemberService memberService;

    @Autowired
    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(MemberLoginDto memberLogin) {
        if (memberLogin.getEmail() == null || memberLogin.getPassword() == null) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        Optional<Member> member = memberService.findMemberByEmail(memberLogin.getEmail());
        if (member.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        String correctPassword = member.get().getPasswordHash();
        String salt = member.get().getSalt();

        // TODO: Check if hash matches
        // Add that later when we have sufficient hashing mechanics

        return ResponseEntity.ok(null);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody MemberRegisterDto memberRegister) {
        // TODO: First check should be to see if all values are not null
        // If any value is not null, return BAD_REQUEST with "Missing data" message in body
        if (memberRegister.getEmail() == null || memberRegister.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("Invalid email");
        }
        if (
                memberRegister.getPassword() == null ||
                        memberRegister.getPassword().isBlank()  ||
                        memberRegister.getRepeatPassword() == null ||
                        memberRegister.getRepeatPassword().isBlank() ||
                        !memberRegister.getPassword().equals(memberRegister.getRepeatPassword())
        ) {
            return ResponseEntity.badRequest().body("Invalid password or repeat password");
        }

        if ( memberRegister.getJmbag() == null || memberRegister.getJmbag().isBlank()) {
            return ResponseEntity.badRequest().body("Invalid jmbag");
        }

        if (
                memberRegister.getFirstName() == null ||
                        memberRegister.getFirstName().isBlank() ||
                        memberRegister.getLastName() == null ||
                        memberRegister.getLastName().isBlank()
        ) {
            return ResponseEntity.badRequest().body("Invalid first name or last name");
        }

        Optional<Member> member1 = memberService.findMemberByEmail(memberRegister.getEmail());
        if (member1.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
        }

        Optional<Member> member2 = memberService.findMemberByJmbag(memberRegister.getJmbag());
        if (member2.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("JMBAG already in use");
        }

        // TODO: Replace null, null with passwordHash and salt
        Member newMember;
        try {
            newMember = new Member(memberRegister.getFirstName(), memberRegister.getLastName(), memberRegister.getJmbag(), memberRegister.getEmail(), null, null);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        memberService.addMember(newMember);
        return ResponseEntity.ok(null);
    }
}
