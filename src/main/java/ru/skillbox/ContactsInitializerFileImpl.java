package ru.skillbox;

import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Component
@Profile("init")
public class ContactsInitializerFileImpl implements ContactInitializer {

    private Set<Contact> contacts;
    private final FileProperties fileProperties;
    private final ResourceLoader resourceLoader;

    public ContactsInitializerFileImpl(FileProperties fileProperties, ResourceLoader resourceLoader) throws IOException {
        this.fileProperties = fileProperties;
        this.resourceLoader = resourceLoader;
        init();
    }

    public void init() {
        contacts = new HashSet<>();
        String filePath = fileProperties.getLoadPath();
        try (Reader reader = new InputStreamReader(resourceLoader.getResource(filePath).getInputStream(), StandardCharsets.UTF_8)) {
            String content = FileCopyUtils.copyToString(reader);
            String[] lines = content.split("\\r?\\n");

            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String fullName = parts[0].trim();
                    String phoneNumber = parts[1].trim();
                    String email = parts[2].trim();
                    contacts.add(new Contact(fullName, phoneNumber, email));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Contact> getContacts() {
        return contacts;
    }
}
