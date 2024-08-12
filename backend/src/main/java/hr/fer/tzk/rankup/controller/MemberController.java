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
        if (memberService.isJmbagInUse(member.getJmbag())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("JMBAG already in use");
        }

        try {
            Member newMember = memberService.createMember(MemberMapper.fromForm(member));
            URI location = new URI("/api/members/" + newMember.getId());
            return ResponseEntity.created(location).build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating member");
        }
    }

    @PutMapping("/{idMember}")
    public ResponseEntity<String> updateMember(@PathVariable Long idMember, @Valid @RequestBody BasicMemberForm member) {
        Optional<Member> memberOpt = memberService.findMemberById(idMember);
        if (memberOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Member> memberWithSameJmbag = memberService.findMemberByJmbag(member.getJmbag());
        if (memberWithSameJmbag.isPresent() && !memberWithSameJmbag.get().getId().equals(idMember)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("JMBAG already in use");
        }
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
