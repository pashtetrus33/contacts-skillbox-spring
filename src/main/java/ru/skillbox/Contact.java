package ru.skillbox;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {
    private String fullName;
    private String phoneNumber;
    private String email;

    @Override
    public String toString() {
        //«Ф. И. О. | Номер телефона | Адрес электронной почты»
        return "«" + fullName + " | " + phoneNumber + " | " + email + "»";
    }

    // Метод для получения строки контакта в нужном формате
    public String toFileFormat() {
        return fullName + ";" + phoneNumber + ";" + email;
    }
}
