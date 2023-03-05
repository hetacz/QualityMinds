package org.example.qualityminds.utils;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Generate {

    private final Faker FAKER = new Faker();

    public String name() {
        return FAKER.name().fullName();
    }
}
