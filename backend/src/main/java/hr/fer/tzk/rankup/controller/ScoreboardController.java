package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.ScoreboardDto;
import hr.fer.tzk.rankup.service.ScoreboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sections/{idSection}/scoreboard")
public class ScoreboardController {

    private final ScoreboardService scoreboardService;

    public ScoreboardController(ScoreboardService scoreboardService) {
        this.scoreboardService = scoreboardService;
    }

    @GetMapping("/total")
    public ResponseEntity<List<ScoreboardDto>> getPointsTotal(@PathVariable Long idSection) {
        List<ScoreboardDto> scoreboard = scoreboardService.getPointsTotal(idSection);
        return ResponseEntity.ok(scoreboard);
    }

    @GetMapping("/semester")
    public ResponseEntity<List<ScoreboardDto>> getPointsSemester(@PathVariable Long idSection) {
        List<ScoreboardDto> scoreboard = scoreboardService.getPointsSemester(idSection);
        return ResponseEntity.ok(scoreboard);
    }

    @GetMapping("/year")
    public ResponseEntity<List<ScoreboardDto>> getPointsYear(@PathVariable Long idSection) {
        List<ScoreboardDto> scoreboard = scoreboardService.getPointsYear(idSection);
        return ResponseEntity.ok(scoreboard);
    }
}
