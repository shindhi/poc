package config;

import model.entities.person.PersonDAOImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.entities.person.PersonDAO;

@Configuration
public class DAOconfig {

    @Bean
    public EntityManager entityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banksp");

        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public PersonDAO personDAO(final EntityManager entityManager) {
        return new PersonDAOImplementation(entityManager);
    }

}
