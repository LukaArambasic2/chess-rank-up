package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.DetailedMemberDto;
import hr.fer.tzk.rankup.form.BasicMemberForm;
import hr.fer.tzk.rankup.mapper.MemberMapper;
import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.service.MemberService;
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
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping
    public ResponseEntity<List<DetailedMemberDto>> findAllMembers() {
        List<Member> members = memberService.findAllMembers();
        List<DetailedMemberDto> detailedMembers = members.stream()
                .map(MemberMapper::toDetailedDto)
                .toList();
        return ResponseEntity.ok(detailedMembers);
    }

    @GetMapping("/{idMember}")
    public ResponseEntity<DetailedMemberDto> findMember(@PathVariable Long idMember) {
        Optional<Member> memberOpt = memberService.findMemberById(idMember);
        if (memberOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Member member = memberOpt.get();
        DetailedMemberDto detailedMember = MemberMapper.toDetailedDto(member);
        return ResponseEntity.ok(detailedMember);
    }


    @PostMapping
    public ResponseEntity<String> createMember(@Valid @RequestBody BasicMemberForm member) throws URISyntaxException {
        Optional<Member> memberWithSameJmbag = memberService.findMemberByJmbag(member.getJmbag());
        if (memberWithSameJmbag.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("JMBAG already in use");
        }

        Member newMember;
        try {
            newMember = new Member(member.getFirstName(), member.getLastName(), member.getJmbag());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
        memberService.createMember(newMember);

        Optional<Member> createdMemberOpt = memberService.findMemberByJmbag(member.getJmbag());
        if (createdMemberOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating member");
        }

        Long createdMemberId = createdMemberOpt.get().getId();
        URI location = new URI("/api/members/" + createdMemberId);

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{idMember}")
    public ResponseEntity<String> updateMember(@PathVariable Long idMember, @Valid @RequestBody BasicMemberForm member) {
        Optional<Member> memberOpt = memberService.findMemberById(idMember);
        if (memberOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

//        Member existingMember = memberOpt.get();
        Optional<Member> memberWithSameJmbag = memberService.findMemberByJmbag(member.getJmbag());
        if (memberWithSameJmbag.isPresent() && !memberWithSameJmbag.get().getId().equals(idMember)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("JMBAG already in use");
        }

//        existingMember.setFirstName(member.getFirstName());
//        existingMember.setLastName(member.getLastName());
//        existingMember.setJmbag(member.getJmbag());
//
//        memberService.updateMember(existingMember);
        memberService.updateMemberFromBasic(idMember, MemberMapper.fromFormToDto(member));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idMember}")
    public ResponseEntity<String> deleteMember(@PathVariable Long idMember) {
        Optional<Member> memberOpt = memberService.findMemberById(idMember);
        if (memberOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        memberService.deleteMemberById(idMember);
        return ResponseEntity.ok().build();
    }
}
