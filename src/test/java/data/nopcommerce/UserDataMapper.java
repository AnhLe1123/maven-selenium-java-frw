package data.nopcommerce;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import commons.GlobalConstants;

import java.io.File;
import java.util.List;

public class UserDataMapper {
    public static UserDataMapper getUserData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(GlobalConstants.TEST_RESOURCES + "UserData.json"), UserDataMapper.class);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("emailAddress")
    private String emailAddress;

    @JsonProperty("password")
    private String password;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("birthDay")
    private String birthDay;

    @JsonProperty("birthMonth")
    private String birthMonth;

    @JsonProperty("birthYear")
    private String birthYear;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public String getBirthYear() {
        return birthYear;
    }

    @JsonProperty("login")
    private Login login;

    static class Login {
        @JsonProperty("username")
        private String username;

        @JsonProperty("password")
        private String password;
    }

    public String getLoginUsername() {
        return login.username;
    }

    public String getLoginPassword() {
        return login.password;
    }

    @JsonProperty("subjects")
    private List<Subject> subjects;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public static class Subject {
        @JsonProperty("name")
        private String name;

        @JsonProperty("point")
        private float point;

        public String getName() {
            return name;
        }

        public float getPoint() {
            return point;
        }
    }


}
