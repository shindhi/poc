package com.invillia;

import com.invillia.model.entities.person.Person;
import com.invillia.model.entities.person.PersonDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        final var applocationContext = new AnnotationConfigApplicationContext("com.invillia");

        final PersonDAO personDAO = applocationContext.getBean("personDAO", PersonDAO.class);
        personDAO.insert(new Person("Diogo", "000.000.00-00"));
    }
}
