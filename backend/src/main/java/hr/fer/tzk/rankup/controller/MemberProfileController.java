package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.ActivityPageDto;
import hr.fer.tzk.rankup.dto.ProfileEventDto;
import hr.fer.tzk.rankup.dto.ProfileGeneralInfoDto;
import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.model.SectionMember;
import hr.fer.tzk.rankup.service.DownloadImageService;
import hr.fer.tzk.rankup.service.MemberProfileService;
import hr.fer.tzk.rankup.service.MemberService;
import hr.fer.tzk.rankup.service.SectionMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/sections/{idSection}/members/{idMember}/profile")
public class MemberProfileController {

    private final MemberProfileService profileService;
    private final DownloadImageService downloadImageService;
    private final MemberService memberService;
    private final SectionMemberService sectionMemberService;

    @Autowired
    public MemberProfileController(MemberProfileService profileService, DownloadImageService downloadImageService, MemberService memberService, SectionMemberService sectionMemberService) {
        this.profileService = profileService;
        this.downloadImageService = downloadImageService;
        this.memberService = memberService;
        this.sectionMemberService = sectionMemberService;
    }

    @GetMapping("/activities")
    public ResponseEntity<ActivityPageDto> getAllActivities(@PathVariable Long idSection, @PathVariable Long idMember) {
        ActivityPageDto activities = profileService.getActivityPage(idSection, idMember);
        if (activities == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/general")
    public ResponseEntity<ProfileGeneralInfoDto> getGeneralInfo(@PathVariable Long idSection, @PathVariable Long idMember) {
        ProfileGeneralInfoDto info = profileService.getGeneralProfileInfo(idSection, idMember);
        if (info == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(info);
    }

    @GetMapping("/rank-icon")
    public ResponseEntity<Void> getRankIcon(@PathVariable Long idSection, @PathVariable Long idMember) {
        return null;
    }

    @GetMapping("/qr-code")
    public ResponseEntity<byte[]> getQrCode(@PathVariable Long idSection, @PathVariable Long idMember) {
        Optional<SectionMember> sectionMemberOpt = sectionMemberService.findSectionMemberByIdSection(idMember, idSection);
        if (sectionMemberOpt.isEmpty()) {
            return null;
        }
        SectionMember member = sectionMemberOpt.get();
        String jmbag = member.getMember().getJmbag();
        int width = 250, height = 250;
        String correctionLevel = "H";
        String filename = "jmbag.png";

        byte[] imageBytes = downloadImageService.createQrCode(jmbag, width, height, correctionLevel);
        if (imageBytes == null) {
            return ResponseEntity.badRequest().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/png");
        headers.set("Content-Disposition", "inline; filename=\"" + filename + "\"");

        return ResponseEntity.ok().headers(headers).body(imageBytes);
    }
}
