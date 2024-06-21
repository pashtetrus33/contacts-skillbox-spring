package ru.skillbox;

import java.util.regex.Pattern;

public class ContactValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern FULLNAME_PATTERN = Pattern.compile("^[А-Яа-яЁёA-Za-z]+\\s[А-Яа-яЁёA-Za-z]+\\s[А-Яа-яЁёA-Za-z]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(\\+7|8)\\d{10}$");

    public static boolean isValidEmail(String email) {
        boolean result = EMAIL_PATTERN.matcher(email).matches();
        if (!result) {
            System.out.println("Введен некорректный email. Пожалуйста, повторите ввод.");
            System.out.println("Пример: email@mail.ru");
        }
        return result;
    }

    public static boolean isValidFullName(String fullName) {
        boolean result = FULLNAME_PATTERN.matcher(fullName).matches();
        if (!result) {
            System.out.println("Введены некорректные ФИО. Пожалуйста, повторите ввод.");
            System.out.println("Пример: Баканов Павел Михайлович");
        }
        return result;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        boolean result = PHONE_PATTERN.matcher(phoneNumber).matches();
        if (!result) {
            System.out.println("Введен некорректный номер телефона. Пожалуйста, повторите ввод.");
            System.out.println("Пример: +79607347899 или 89607347899");
        }
        return result;
    }

    public static Contact isValidString(String input) {
        String[] parts = input.split(";");

        if (parts.length != 3) {
            System.out.println("Ошибка: введенная строка должна содержать три части, разделенные двумя ';'. Пожалуйста, повторите ввод.");
            System.out.println("Пример: Баканов Павел Михайлович; +79607347899; email@mail.ru");
            return null;
        }

        String fullName = parts[0].trim();
        String phoneNumber = parts[1].trim();
        String email = parts[2].trim();

        if (!isValidFullName(fullName)) {
            return null;
        }
        if (!isValidPhoneNumber(phoneNumber)) {
            return null;
        }
        if (!isValidEmail(email)) {
            return null;
        }
        return new Contact(fullName, phoneNumber, email);
    }
}
