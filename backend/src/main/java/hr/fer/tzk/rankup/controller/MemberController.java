package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.repository.MemberRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tmp")
public class MemberController {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@Valid @RequestBody Member member) throws URISyntaxException {
        boolean emailExists = member.getEmail() != null;
        if (emailExists) {
            Optional<Member> existingMember = memberRepository.findByEmail(member.getEmail());
            if (existingMember.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(existingMember.get());
            }
        }
        Optional<Member> existingMember = memberRepository.findByJmbag(member.getJmbag());
        if (existingMember.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Member savedMember = memberRepository.save(member);
        URI location = new URI("/members/" + savedMember.getId());
        return ResponseEntity.created(location).body(savedMember);
    }
}
