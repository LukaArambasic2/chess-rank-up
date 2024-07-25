package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/csv")
public class CsvController {
    @Autowired
    private CsvService csvService;

    @PostMapping("/upload/lichess")
    public ResponseEntity<String> uploadLichessCsv(@RequestParam("file") MultipartFile file,
                                            @RequestParam("eventName") String eventName,
                                            @RequestParam("defaultPoints") int defaultPoints) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        try {
            csvService.convertLichessCsvToDb(file.getInputStream(), eventName, defaultPoints);
            return ResponseEntity.ok("CSV file has been uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}