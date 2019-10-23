package model.entities.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;


@Component
public class PersonDAOImplementation implements PersonDAO {
    private EntityManager entityManager;

    @Autowired
    public PersonDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void insert(Person person) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(person);
        transaction.commit();
    }

    @Override
    public List<Person> findAll() {
        final TypedQuery<Person> person = entityManager.createQuery(
                "from Person",
                Person.class
        );

        return person.getResultList();
    }

    @Override
    public Person findById(final Long id) {
        return entityManager.find(Person.class, id);
    }

    public void update(final Person person) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.merge(person);
        transaction.commit();
    }

    public void delete(final Long id) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        final Person person = findById(id);
        entityManager.remove(person);
        transaction.commit();
    }
}
