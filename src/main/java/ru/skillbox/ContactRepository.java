package ru.skillbox;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@NoArgsConstructor
public class ContactRepository {
    private final Set<Contact> contactSet = new HashSet<>();

    public boolean deleteContactByEmail(final String email) {
        return contactSet.removeIf(contact -> contact.getEmail().equals(email));
    }

    public Set<Contact> getAll() {
        return new HashSet<>(contactSet); // Возвращаем новую копию множества для защиты от модификации
    }

    public void add(Contact contact) {
        contactSet.add(contact);
    }

    public boolean contains(Contact contact) {
        return contactSet.contains(contact);
    }

    public boolean isEmpty() {
        return contactSet.isEmpty();
    }

    public void setAll(Set<Contact> contacts) {
        contactSet.addAll(contacts);
    }
}