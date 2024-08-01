package hr.fer.tzk.rankup.service;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import hr.fer.tzk.rankup.utils.QrCodeUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class DownloadImageService {

    public byte[] createQrCode(String text, int width, int height, String correctionLevel) {
        ErrorCorrectionLevel errorCorrectionLevel = switch (correctionLevel.toUpperCase()) {
            case "L" -> ErrorCorrectionLevel.L;
            case "M" -> ErrorCorrectionLevel.M;
            case "Q" -> ErrorCorrectionLevel.Q;
            default -> ErrorCorrectionLevel.H;
        };

        try {
            BufferedImage qrCodeImage = QrCodeUtils.generateQRCode(text, errorCorrectionLevel, width, height);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrCodeImage, "png", baos);
            return baos.toByteArray();
        } catch (WriterException | IOException e) {
            return null;
        }
    }
}
