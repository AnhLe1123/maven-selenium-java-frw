package com.nopcommerce.user;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import commons.GlobalConstants;

import java.io.File;
import java.io.IOException;

public class Level_21_ReadWriteJSON_Extra {
    public static void main(String[] args) {
        Level_21_ReadWriteJSON_Extra tester = new Level_21_ReadWriteJSON_Extra();
        try {
            Students student = new Students();
            student.setAge(10);
            student.setName("Madesh");
            tester.writeJSON(student);
            Students student1 = tester.readJSON();
            System.out.println(student1);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeJSON(Students student) throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(GlobalConstants.TEST_RESOURCES + "JacksonDataTest.json"), student);
    }

    private Students readJSON() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Students student = mapper.readValue(new File(GlobalConstants.TEST_RESOURCES + "JacksonDataTest.json"), Students.class);
        return student;
    }
}

class Students {

    private String name;
    private int age;

    public Students() {

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
