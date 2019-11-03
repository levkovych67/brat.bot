 import obj.CaptionDTO;
 import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class CaptionService {

    private static String generateRandomFact() {
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<CaptionDTO> forEntity =
                restTemplate.getForEntity("https://uselessfacts.jsph.pl/random.json?language=en", CaptionDTO.class);
        return Objects.requireNonNull(forEntity.getBody()).getText();
    }

    private static String getNumberFact() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity("http://numbersapi.com/random", String.class).getBody();
    }

    private static String getCatFact() {
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<CaptionDTO> forEntity =
                restTemplate.getForEntity("https://cat-fact.herokuapp.com/facts/random", CaptionDTO.class);
        return Objects.requireNonNull(forEntity.getBody()).getText();
    }

    public static String getFACT() {
        try {
            final int i = new Random().nextInt(8);
            if (Arrays.asList(0, 1).contains(i)) {
                return generateRandomFact();
            }
            if (Arrays.asList(4, 5).contains(i)) {
                return getNumberFact();
            }
            if (Arrays.asList(6, 7).contains(i)) {
                return getCatFact();
            }
            if (Arrays.asList(2, 3, 8).contains(i)) {
                return "";
            } else {
                return "ФАКТИ КОНЧИЛИСЬ РАЗХОДИМСЯ";
            }
        } catch (Exception e) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            return getFACT();
        }

    }

}
