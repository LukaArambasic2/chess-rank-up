package hr.fer.tzk.rankup.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QRCodeUtils {
    /**
     * Generates a QR code image from the provided text and returns it as a BufferedImage. <br>
     * The final image will be of size <b>width x height</b>. <br><br>
     * The QR code can contain different amounts of redundant correction bits, making it more or less reliable. <br>
     * Options for error correction levels are:
     * <ul>
     *   <li><b>ErrorCorrectionLevel.L</b>: Low - Recovers 7% of data</li>
     *   <li><b>ErrorCorrectionLevel.M</b>: Medium - Recovers 15% of data</li>
     *   <li><b>ErrorCorrectionLevel.Q</b>: Quartile - Recovers 25% of data</li>
     *   <li><b>ErrorCorrectionLevel.H</b>: High - Recovers 30% of data</li>
     * </ul>
     *
     * @param text The text to encode in the QR code.
     * @param errorCorrectionLevel The error correction level for the QR code.
     * @param width The width of the QR code image.
     * @param height The height of the QR code image.
     * @return The generated QR code as a BufferedImage.
     * @throws WriterException If there is an error generating the QR code.
     */
    public static BufferedImage generateQRCode(String text,
                                               ErrorCorrectionLevel errorCorrectionLevel,
                                               int width,
                                               int height)
            throws WriterException {
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        // Since everything in QR code is either a black dot (binary '1') or a white dot (binary '0'), we can just use
        // a binary matrix.
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hintMap);

        // Convert BitMatrix to BufferedImage
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     * Reads a QR code from a BufferedImage and returns the decoded text.
     *
     * @param bufferedImage the BufferedImage containing the QR code
     * @return the decoded text from the QR code
     * @throws IOException if there is an error while reading the image
     */
    public static String readQrCode(BufferedImage bufferedImage) throws IOException {
        // Luminance source is just a grayscale version of original image.
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);

        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (Exception e) {
            throw new IOException("Could not decode QR Code, IOException :: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String text = "Sup every1, this is Peter. HALOOOOO";
        ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.H;
        int width = 1000;
        int height = 1000;

        try {
            BufferedImage qrCodeImage = QRCodeUtils.generateQRCode(text, errorCorrectionLevel, width, height);
            System.out.println("QR Code generated successfully.");

            // Optionally, save the image to a file for verification
            File outputfile = new File("generatedQRCode1.png");
            ImageIO.write(qrCodeImage, "png", outputfile);

            // Read the QR code from the BufferedImage
            String decodedText = QRCodeUtils.readQrCode(qrCodeImage);
            System.out.println("Decoded text from QR code: " + decodedText);
        } catch (WriterException | IOException e) {
            System.err.println("Error while generating or reading QR Code: " + e.getMessage());
        }
    }
}
