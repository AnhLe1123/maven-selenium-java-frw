package utilities;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {
    private Locale locale = new Locale("en");
    private Faker faker = new Faker(locale);

    public static DataHelper getData() {
        return new DataHelper();
    }

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getEmailAddress() {
        return faker.internet().emailAddress();
    }

    public String getPassword() {
        return faker.internet().password(8, 12, true, true);
    }

    public String getMobilePhone() {
        return faker.phoneNumber().cellPhone();
    }

    public String getAddress() {
        return faker.address().fullAddress();
    }

    public String getCity() {
        return faker.address().city();
    }

    public String getCountry() {
        return faker.address().country();
    }

    public String getZipCode() {
        return faker.address().zipCode();
    }

    public String getCompanyName() {
        return faker.company().name();
    }
}
