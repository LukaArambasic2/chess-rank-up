package hr.fer.tzk.rankup.controller;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import hr.fer.tzk.rankup.utils.QRCodeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/download/image")
public class DownloadImageController {

    @GetMapping("/qrcode/{text}")
    public ResponseEntity<byte[]> createQRCode(
            @PathVariable String text,
            @RequestParam(required = false, defaultValue = "qrcode.png") String filename,
            @RequestParam(required = false, defaultValue = "250") int width,
            @RequestParam(required = false, defaultValue = "250") int height,
            @RequestParam(required = false, defaultValue = "H") String errorCorrection
    ) {
        if (height == 0 || width == 0 || filename == null || filename.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        ErrorCorrectionLevel errorCorrectionLevel = switch (errorCorrection.toUpperCase()) {
            case "L" -> ErrorCorrectionLevel.L;
            case "M" -> ErrorCorrectionLevel.M;
            case "Q" -> ErrorCorrectionLevel.Q;
            default -> ErrorCorrectionLevel.H;
        };

        try {
            BufferedImage qrCodeImage = QRCodeUtils.generateQRCode(text, errorCorrectionLevel, width, height);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrCodeImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "image/png");
            headers.set("Content-Disposition", "inline; filename=\"" + filename + "\"");

            return ResponseEntity.ok().headers(headers).body(imageBytes);
        } catch (WriterException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
