package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.DetailedMemberDto;
import hr.fer.tzk.rankup.mapper.MemberMapper;
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
@RequestMapping("/api/members")
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

    // TODO: Add all of this as forms and mappers
//    private ResponseEntity<String> validateMemberDto(MemberNotRegisteredDto memberDto) {
//        if (memberDto.getFirstName() == null || memberDto.getFirstName().isBlank()) {
//            return ResponseEntity.badRequest().body("Missing first name");
//        }
//
//        if (memberDto.getLastName() == null || memberDto.getLastName().isBlank()) {
//            return ResponseEntity.badRequest().body("Missing last name");
//        }
//
//        if (memberDto.getFirstName().length() > 30) {
//            return ResponseEntity.badRequest().body("First name too long");
//        }
//
//        if (memberDto.getLastName().length() > 30) {
//            return ResponseEntity.badRequest().body("Last name too long");
//        }
//
//        if (memberDto.getJmbag() == null || memberDto.getJmbag().isBlank()) {
//            return ResponseEntity.badRequest().body("Missing JMBAG");
//        }
//
//        return null;
//    }

//    private ResponseEntity<String> validateMemberGlobalDto(MemberGlobalDto memberDto) {
//        if (memberDto.getFirstName() == null || memberDto.getFirstName().isBlank()) {
//            return ResponseEntity.badRequest().body("Missing first name");
//        }
//
//        if (memberDto.getLastName() == null || memberDto.getLastName().isBlank()) {
//            return ResponseEntity.badRequest().body("Missing last name");
//        }
//
//        if (memberDto.getFirstName().length() > 30) {
//            return ResponseEntity.badRequest().body("First name too long");
//        }
//
//        if (memberDto.getLastName().length() > 30) {
//            return ResponseEntity.badRequest().body("Last name too long");
//        }
//
//        if (memberDto.getJmbag() == null || memberDto.getJmbag().isBlank()) {
//            return ResponseEntity.badRequest().body("Missing JMBAG");
//        }
//
//        if (memberDto.getEmail() == null || memberDto.getEmail().isBlank()) {
//            return ResponseEntity.badRequest().body("Missing email");
//        }
//
//        return null;
//    }

//    @PostMapping
//    public ResponseEntity<String> createMember(@RequestBody MemberNotRegisteredDto memberDto) throws URISyntaxException {
//        ResponseEntity<String> validationResponse = validateMemberDto(memberDto);
//        if (validationResponse != null) {
//            return validationResponse;
//        }
//
//        Optional<Member> memberWithSameJmbag = memberService.findMemberByJmbag(memberDto.getJmbag());
//        if (memberWithSameJmbag.isPresent()) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("JMBAG already in use");
//        }
//
//        Member newMember;
//        try {
//            newMember = new Member(memberDto.getFirstName(), memberDto.getLastName(), memberDto.getJmbag());
//        } catch (IllegalArgumentException exception) {
//            return ResponseEntity.badRequest().body(exception.getMessage());
//        }
//        memberService.createMember(newMember);
//
//        Optional<Member> createdMemberOpt = memberService.findMemberByJmbag(memberDto.getJmbag());
//        if (createdMemberOpt.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating member");
//        }
//
//        Long createdMemberId = createdMemberOpt.get().getId();
//        URI location = new URI("/api/members/" + createdMemberId);
//
//        return ResponseEntity.created(location).build();
//    }
//
//    @PutMapping("/{idMember}")
//    public ResponseEntity<String> updateMember(@PathVariable Long idMember, @RequestBody MemberGlobalDto memberDto) {
//        ResponseEntity<String> validationResponse = validateMemberGlobalDto(memberDto);
//        if (validationResponse != null) {
//            return validationResponse;
//        }
//
//        Optional<Member> memberOpt = memberService.findMemberById(idMember);
//        if (memberOpt.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Member existingMember = memberOpt.get();
//
//        Optional<Member> memberWithSameJmbag = memberService.findMemberByJmbag(memberDto.getJmbag());
//        if (memberWithSameJmbag.isPresent() && !memberWithSameJmbag.get().getId().equals(idMember)) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("JMBAG already in use");
//        }
//
//        Optional<Member> memberWithSameEmail = memberService.findMemberByEmail(memberDto.getEmail());
//        if (memberWithSameEmail.isPresent() && !memberWithSameEmail.get().getId().equals(idMember)) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
//        }
//
//        existingMember.setFirstName(memberDto.getFirstName());
//        existingMember.setLastName(memberDto.getLastName());
//        existingMember.setJmbag(memberDto.getJmbag());
//        existingMember.setEmail(memberDto.getEmail());
//
//        memberService.updateMember(existingMember);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{idMember}")
//    public ResponseEntity<String> deleteMember(@PathVariable Long idMember) {
//        Optional<Member> memberOpt = memberService.findMemberById(idMember);
//        if (memberOpt.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        memberService.deleteMemberById(idMember);
//        return ResponseEntity.ok().build();
//    }
}
