package ru.skillbox;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Profile("default")
public class ContactsInitializerDefault implements ContactInitializer {
    public Set<Contact> getContacts() {
        return null;
    }
}
