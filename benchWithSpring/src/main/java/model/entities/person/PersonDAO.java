package model.entities.person;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public interface PersonDAO {
    void insert(Person person);

    Person findById(Long id);

    List<Person> findAll();

}
