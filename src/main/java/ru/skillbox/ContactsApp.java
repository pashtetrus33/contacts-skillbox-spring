package ru.skillbox;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ContactsApp {

    private final ContactInitializer contactsInitializer;

    public void start() {
        ContactRepository contactRepository = new ContactRepository();

        if (contactsInitializer != null && contactsInitializer.getContacts() != null) {
            contactRepository.setAll(contactsInitializer.getContacts());
            System.out.println("Контакты проинициализированы из файла.");
        } else {
            System.out.println("Контакты не инициализированы.");
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Ввести контакт");
            System.out.println("2. Показать введенные данные");
            System.out.println("3. Удалить контакт по email");
            System.out.println("4. Сохранить контакты в файл");
            System.out.println("5. Выход");
            System.out.print("Выберите пункт меню: ");

            int choice = getValidatedChoice(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Введите ФИО, номер телефона и email через точку с запятой (;): ");
                    String input = scanner.nextLine();
                    Contact contact = ContactValidator.isValidString(input);
                    if (contact == null) {
                        break;
                    }
                    if (contactRepository.contains(contact)) {
                        System.out.println("Контакт уже существует.");
                    } else {
                        contactRepository.add(contact);
                        System.out.println("Данные успешно сохранены.");
                    }
                    break;

                case 2:
                    System.out.println("Все контакты:");
                    if (contactRepository.isEmpty()) {
                        System.out.println("Сохраненных контактов нет.");
                    } else {
                        contactRepository.getAll().forEach(System.out::println);
                    }
                    break;

                case 3:
                    System.out.print("Введите email для удаления: ");
                    String email = scanner.nextLine();
                    if (ContactValidator.isValidEmail(email)) {
                        System.out.println(contactRepository.deleteContactByEmail(email) ? "Контакт/ы удален/ы" : "Контакт не найден");
                    }
                    break;

                case 4:
                    if (contactRepository.isEmpty()) {
                        System.out.println("Сохранять в файл нечего. Контактов нет.");
                    } else {
                        ContactSaver.saveContactsToFile(contactRepository.getAll(),
                                Paths.get("src", "main", "resources") + "/default-contacts.txt");
                        System.out.println("Контакты успешно сохранены в файл default-contacts.txt");
                    }
                    break;

                case 5:
                    System.out.println("Выход из программы...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Неправильный выбор. Пожалуйста, выберите пункт меню от 1 до 3.");
            }
        }
    }

    private int getValidatedChoice(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера
                return choice;
            } else {
                System.out.println("Ошибка: введено некорректное значение. Пожалуйста, введите число: ");
                scanner.next(); // Очищаем некорректный ввод
            }
        }
    }
}