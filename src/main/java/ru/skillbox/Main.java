package ru.skillbox;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ContactsApp contactsApp = context.getBean(ContactsApp.class);
        contactsApp.start();
    }
}