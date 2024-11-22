package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.LeaderboardDto;
import hr.fer.tzk.rankup.service.LeaderboardService;
import hr.fer.tzk.rankup.service.ScoreboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sections/leaderboard")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

//    @GetMapping
//    public ResponseEntity<LeaderboardDto> getLeaderboard(@RequestParam )
}
