package helper;

import com.github.javafaker.Faker;

import java.io.File;
import java.util.*;

public class JavaHelper {

    private static final Faker faker = new Faker();


    public static File getJSONFile(String JSONFile) {
        return new File(JSONFile);
    }

    public static String generateRandomFirstName() {
        return faker.name().firstName();
    }

    public static String generateRandomGender() {
        Random random = new Random();
        List<String> genders = new ArrayList<>();
        genders.add("male");
        genders.add("female");

        return genders.get(random.nextInt(genders.size()));
    }

    public static String generateRandomStatus() {
        Random random = new Random();
        List<String> status = new ArrayList<>();
        status.add("active");
        status.add("inactive");

        return status.get(random.nextInt(status.size()));
    }

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }


}
