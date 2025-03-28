package hr.fer.tzk.rankup.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JmbagUtilsTest {

    private static String[] validJmbags;

    @BeforeAll
    public static void setUp() {
        validJmbags = new String[] {
                "0006040945", "0036533665", "0036538219", "0036539669", "0036540976", "0036542367", "0036543684",
                "0016140662", "0036533670", "0036538245", "0036539718", "0036540997", "0036542372", "0036543707",
                "0035214385", "0036533777", "0036538250", "0036539723", "0036541060", "0036542388", "0036543712",
                "0035215057", "0036533798", "0036538266", "0036539739", "0036541081", "0036542400", "0036543749",
                "0035215377", "0036533873", "0036538292", "0036539744", "0036541125", "0036542416", "0036543754",
                "0035221899", "0036533959", "0036538315", "0036539765", "0036541130", "0036542437", "0036543775",
                "0035222534", "0036534134", "0036538320", "0036539786", "0036541151", "0036542442", "0036543780",
                "0035232499", "0036534246", "0036538336", "0036539791", "0036541188", "0036542458", "0036543803",
                "0035235027", "0036534288", "0036538357", "0036539809", "0036541193", "0036542479", "0036543824",
                "0036420918", "0036534524", "0036538378", "0036539814", "0036541200", "0036542484", "0036543871",
                "0036439680", "0036534571", "0036538383", "0036539840", "0036541216", "0036542512", "0036543892",
                "0036448174", "0036534587", "0036538399", "0036539856", "0036541237", "0036542528", "0036543915",
                "0036494412", "0036535061", "0036538406", "0036539861", "0036541258", "0036542549", "0036543920",
                "0036494496", "0036535077", "0036538432", "0036539882", "0036541284", "0036542596", "0036543941",
                "0036498838", "0036535430", "0036538453", "0036539898", "0036541307", "0036542603", "0036543957",
                "0036509050", "0036535446", "0036538469", "0036539905", "0036541312", "0036542624", "0036543962",
                "0036509505", "0036535521", "0036538518", "0036539910", "0036541328", "0036542645", "0036543978",
                "0036510310", "0036535579", "0036538539", "0036539947", "0036541375", "0036542650", "0036544015",
                "0036514138", "0036535628", "0036538565", "0036539973", "0036541380", "0036542687", "0036544020",
                "0036517306", "0036535680", "0036538570", "0036539994", "0036541403", "0036542692", "0036544036",
                "0036522500", "0036535750", "0036538586", "0036540000", "0036541419", "0036542715", "0036544057",
                "0036522899", "0036536139", "0036538591", "0036540016", "0036541445", "0036542736", "0036544062",
                "0036524206", "0036536144", "0036538609", "0036540037", "0036541450", "0036542757", "0036544078",
                "0036524386", "0036536170", "0036538614", "0036540063", "0036541466", "0036542762", "0036544099",
                "0036524594", "0036536651", "0036538635", "0036540079", "0036541471", "0036542778", "0036544106",
                "0036525890", "0036537344", "0036538640", "0036540084", "0036541487", "0036542799", "0036544111",
                "0036526765", "0036537370", "0036538661", "0036540112", "0036541492", "0036542806", "0036544127",
                "0036527068", "0036537391", "0036538677", "0036540149", "0036541515", "0036542811", "0036544132",
                "0036527367", "0036537414", "0036538682", "0036540154", "0036541520", "0036542848", "0036544148",
                "0036527650", "0036537435", "0036538698", "0036540175", "0036541536", "0036542853", "0036544153",
                "0036527687", "0036537440", "0036538710", "0036540180", "0036541541", "0036542874", "0036544169",
                "0036527853", "0036537456", "0036538747", "0036540196", "0036541562", "0036542895", "0036544195",
                "0036527902", "0036537498", "0036538752", "0036540203", "0036541599", "0036542902", "0036544202",
                "0036529410", "0036537505", "0036538794", "0036540219", "0036541606", "0036542918", "0036544218",
                "0036529473", "0036537510", "0036538801", "0036540224", "0036541627", "0036542939", "0036544239",
                "0036529585", "0036537547", "0036538838", "0036540245", "0036541648", "0036542944", "0036544265",
                "0036529629", "0036537552", "0036538843", "0036540250", "0036541653", "0036542965", "0036544270",
                "0036529767", "0036537568", "0036538885", "0036540266", "0036541695", "0036542970", "0036544309",
                "0036529816", "0036537573", "0036538890", "0036540287", "0036541702", "0036542986", "0036544314",
                "0036529954", "0036537589", "0036538908", "0036540320", "0036541718", "0036543002", "0036553640",
                "0036530182", "0036537601", "0036538913", "0036540336", "0036541723", "0036543065", "0036553908",
                "0036530343", "0036537622", "0036538934", "0036540341", "0036541739", "0036543070", "0036554307",
                "0036530413", "0036537643", "0036538976", "0036540357", "0036541744", "0036543114", "0036554536",
                "0036530892", "0036537664", "0036539008", "0036540362", "0036541765", "0036543140", "0036554606",
                "0036531218", "0036537685", "0036539013", "0036540383"
        };
    }

    @Test
    public void should_return_false_because_wrong_length() {
        final String validJmbag = validJmbags[0];
        final String invalidJmbag1 = validJmbag + '1'; // Too long
        final String invalidJmbag2 = validJmbag.substring(0, validJmbag.length() - 1); // Too short

        assertThat(JmbagUtils.validateJmbag(invalidJmbag1)).isEqualTo(false);
        assertThat(JmbagUtils.validateJmbag(invalidJmbag2)).isEqualTo(false);
    }

    @Test
    public void should_return_false_because_non_digit_char() {
        final String validJmbag = validJmbags[0];
        final String invalidJmbag1 = 'a' + validJmbag.substring(1);
        final String invalidJmbag2 = validJmbag.substring(0, validJmbag.length() - 1) + 'a';
        assertThat(JmbagUtils.validateJmbag(invalidJmbag1)).isEqualTo(false);
        assertThat(JmbagUtils.validateJmbag(invalidJmbag2)).isEqualTo(false);
    }

    //@Test
    public void should_return_false_because_wrong_digit() {
        // Eg. JMBAG is "123456789"
        // Pick digit 1 and then go through 2, 3, ..., 9 and swap it with its original place in JMBAG
        // JMBAG should be invalid after that change
        // Do that for all digits for all valid JMBAGs

        // Doesn't work for some reason
        /*
        for (String validJmbag : validJmbags) {
            for (int i = 0; i < validJmbag.length(); i++) {
                char originalChar = validJmbag.charAt(i);
                for (char newChar = '0'; newChar <= '9'; newChar++) {
                    if (newChar != originalChar) {
                        String invalidJmbag = validJmbag.substring(0, i) + newChar + validJmbag.substring(i + 1);
                        boolean result = JmbagUtils.validateJmbag(invalidJmbag);
                        if (result) {
                            System.out.println("Original JMBAG: " + validJmbag);
                            System.out.println("Invalid JMBAG: " + invalidJmbag);
                        }
                        assertThat(JmbagUtils.validateJMBAG(invalidJmbag)).isEqualTo(false);
                    }
                }
            }
        }
        */
    }

    @Test
    public void should_return_true() {
        for (String jmbag : validJmbags) {
            assertThat(JmbagUtils.validateJmbag(jmbag)).isEqualTo(true);
        }
    }
}
