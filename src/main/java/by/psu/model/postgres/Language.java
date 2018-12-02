package by.psu.model.postgres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public enum  Language {

    RU (UUID.fromString("36602afd-1a67-4a21-8b0f-e9cb54ff5e05"), "Russian"),
    EN (UUID.fromString("064e54e0-602a-49c2-a99d-d7eabd4169d7"), "English");

    UUID uuid = null;
    String value = null;

}
