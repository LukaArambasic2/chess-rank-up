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
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class QRCodeUtils {
    /**
     * Generates a QR code image from the provided text and saves it into <i>{directory}/{fileName}</i>. <br>
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
     * @param directory The directory where the QR code image will be saved.
     * @param fileName The name of the file (including extension) for the generated QR code image.
     * @param errorCorrectionLevel The error correction level for the QR code.
     * @param width The width of the QR code image.
     * @param height The height of the QR code image.
     * @throws WriterException If there is an error generating the QR code.
     * @throws IOException If there is an error saving the QR code image.
     */
    public static void generateQRCode(String text,
                                      Path directory,
                                      String fileName,
                                      ErrorCorrectionLevel errorCorrectionLevel,
                                      int width,
                                      int height)
            throws WriterException, IOException {
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        // Since everything in QR code is either a black dot (binary '1') or a white dot (binary '0'), we can just use
        // a binary matrix.
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hintMap);

        String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
        Path path = directory.resolve(fileName);
        MatrixToImageWriter.writeToPath(bitMatrix, fileType, path);
    }

    /**
     * Reads a QR code from an image file and returns the decoded text.
     *
     * @param qrCodeImage the path to the image file containing the QR code
     * @return the decoded text from the QR code
     * @throws IOException if there is an error while reading the image file
     */
    public static String readQRCode(File qrCodeImage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeImage);

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
        //String text = "0036512345";
        String text = "Sup every1, this is Peter. HALOOOOO";
        Path directory = Paths.get(System.getProperty("java.io.tmpdir"));
        String fileName = "generatedQRCode1.png";
        ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.H;
        int width = 1000;
        int height = 1000;

        try {
            QRCodeUtils.generateQRCode(text, directory, fileName, errorCorrectionLevel, width, height);
            System.out.println("QR Code generated successfully in the home directory.");
        } catch (WriterException | IOException e) {
            System.err.println("Error while generating QR Code: " + e.getMessage());
        }

        try {
            File file = new File(directory.resolve(fileName).toString());
            String decodedText = QRCodeUtils.readQRCode(file);
            System.out.println("Decoded text from QR code: " + decodedText);
        } catch (Exception e) {
            System.err.println("Error while reading QR code: " + e.getMessage());
        }

        try {
            File file = new File(directory.resolve("onlineQRCode1.png").toString());
            String decodedText = QRCodeUtils.readQRCode(file);
            System.out.println("Decoded text from QR code: " + decodedText);
        } catch (Exception e) {
            System.err.println("Error while reading QR code: " + e.getMessage());
        }
    }
}
