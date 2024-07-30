package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.SectionMemberGetDto;
import hr.fer.tzk.rankup.dto.SectionMemberPostDto;
import hr.fer.tzk.rankup.dto.SectionMemberProfileDto;
import hr.fer.tzk.rankup.model.Section;
import hr.fer.tzk.rankup.model.SectionMember;
import hr.fer.tzk.rankup.service.SectionMemberService;
import hr.fer.tzk.rankup.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sections/{idSection}/members")
public class SectionMemberController {

    private final SectionMemberService sectionMemberService;

    @Autowired
    public SectionMemberController(SectionMemberService sectionMemberService) {
        this.sectionMemberService = sectionMemberService;
    }

    /**
     * Handles GET requests to retrieve all section members for a given section ID.
     *
     * @role ADMIN
     * @param idSection the ID of the section whose members are to be retrieved.
     * @return ResponseEntity containing a list of SectionMemberGetDto objects and an HTTP OK status.
     */
    @GetMapping
    public ResponseEntity<List<SectionMemberGetDto>> findAllSectionMembers(@PathVariable Long idSection) {
        return ResponseEntity.ok(sectionMemberService.findAllSectionMembersBySectionId(idSection));
    }

    /**
     * Handles GET request to retrieve specific section member by sectionId.
     *
     * @roles ADMIN
     * @param idSection the ID of the section to which the member belongs.
     * @param idMember the ID of the member to be retrieved.
     * @return ResponseEntity containing the SectionMemberGetDto object and an HTTP OK status if found,
     *         or an HTTP Not Found status if the member does not exist.
     */
    @GetMapping("/{idMember}")
    public ResponseEntity<SectionMemberGetDto> findSectionMemberById(@PathVariable Long idSection, @PathVariable Long idMember) {
        Optional<SectionMemberGetDto> sectionMemberOpt = sectionMemberService.findSectionMemberBySectionId(idSection, idMember);
        if (sectionMemberOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        SectionMemberGetDto sectionMember = sectionMemberOpt.get();
        return ResponseEntity.ok(sectionMember);
    }

    @GetMapping("/profile/{idMember}")
    public ResponseEntity<SectionMemberProfileDto> findMemberProfileById(@PathVariable Long idSection, @PathVariable Long idMember) {
        //Optional<SectionMemberProfileDto> sectionMemberProfileDtoOpt = sectionMemberService.findSectionMemberProfileBySectionId(idSection, idMember);
        return null;
    }
//    @PostMapping
//    public ResponseEntity<SectionMemberPostDto> createSectionMember(@PathVariable Long idSection, @RequestBody SectionMemberPostDto) {
//
//    }

}
