package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<Member>> getMembers() {
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @GetMapping("/{idMember}")
    public ResponseEntity<Member> getMember(@PathVariable Long idMember) {
        Optional<Member> member = memberService.findMemberById(idMember);
        if (member.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(member.get());
    }

    // TODO: Fix this function
    // It should return BAD_REQUEST if JMBAG or EMAIL already exist
    @PostMapping
    public ResponseEntity<String> createMember(@Valid @RequestBody Member member) {
        // Check if there exists a user with the same JMBAG
        Optional<Member> memberWithSameJmbag = memberService.findMemberByJmbag(member.getJmbag());
        if (memberWithSameJmbag.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("JMBAG already exists");
        }

        // Check if there exists a user with the same email
        Optional<Member> memberWithSameEmail = memberService.findMemberByEmail(member.getEmail());
        if (memberWithSameEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        memberService.addMember(member);
        return ResponseEntity.ok().build();
    }
}
