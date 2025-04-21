package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.ActivityPageDto;
import hr.fer.tzk.rankup.dto.ProfileGeneralInfoDto;
import hr.fer.tzk.rankup.service.MemberProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/sections/{idSection}/members/{idMember}/profile")
public class MemberProfileController {

    private final MemberProfileService profileService;

    @Autowired
    public MemberProfileController(MemberProfileService profileService) {
        this.profileService = profileService;
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
}
