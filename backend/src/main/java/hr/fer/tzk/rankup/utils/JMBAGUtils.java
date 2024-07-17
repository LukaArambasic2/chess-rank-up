package hr.fer.tzk.rankup.utils;

public class JMBAGUtils {

    /**
     * Checks if JMBAG is valid. <br> <br>
     *
     * For more details about how the algorithm actually works, read the source code.
     *
     * @param JMBAG JMBAG that will be validated.
     * @return True if JMBAG is valid, False otherwise
     */
    public static boolean validateJMBAG(String JMBAG) {
        // Regex checks if all characters are digits.
        if (JMBAG.length() != 10 || !JMBAG.matches("\\d+")) {
            return false;
        }

        final int[] JMBAGWeights = {2, 3, 4, 5, 1, 2, 3, 4, 5};
        int totalSum = 0;
        final int controlDigit = Character.getNumericValue(JMBAG.charAt(9));
        final String firstNineDigits = JMBAG.substring(0, 9);

        for (int i = 0; i < 9; i++) {
            totalSum += Character.getNumericValue(firstNineDigits.charAt(i)) * JMBAGWeights[i];
        }

        int controlDigitCalculated = 11 - (totalSum % 11);
        if (controlDigitCalculated == 10 || controlDigitCalculated == 11) {
            controlDigitCalculated = 0;
        }

        return controlDigitCalculated == controlDigit;
    }
}
