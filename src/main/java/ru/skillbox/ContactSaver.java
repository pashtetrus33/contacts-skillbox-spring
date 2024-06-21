package ru.skillbox;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class ContactSaver {

    public static void saveContactsToFile(Set<Contact> contacts, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Contact contact : contacts) {
                writer.write(contact.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}