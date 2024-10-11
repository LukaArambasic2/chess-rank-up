package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.service.DownloadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/download/image")
public class DownloadImageController {

    private final DownloadImageService downloadImageService;

    @Autowired
    public DownloadImageController(DownloadImageService downloadImageService) {
        this.downloadImageService = downloadImageService;
    }

    @GetMapping("/qr-code")
    public ResponseEntity<byte[]> createQrCode(
            @RequestParam String text,
            @RequestParam(required = false, defaultValue = "qrcode.png") String filename,
            @RequestParam(required = false, defaultValue = "250") int width,
            @RequestParam(required = false, defaultValue = "250") int height,
            @RequestParam(required = false, defaultValue = "H") String correctionLevel
    ) {
        if (text == null || text.isBlank() || height == 0 || width == 0 || filename == null || filename.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        byte[] imageBytes = downloadImageService.createQrCode(text, width, height, correctionLevel);
        if (imageBytes == null) {
            return ResponseEntity.badRequest().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/png");
        headers.set("Content-Disposition", "inline; filename=\"" + filename + "\"");

        return ResponseEntity.ok().headers(headers).body(imageBytes);
    }
}
