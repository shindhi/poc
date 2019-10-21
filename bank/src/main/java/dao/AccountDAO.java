package dao;

import entities.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountDAO {
    private final EntityManager entityManager;

    public AccountDAO(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(final Account account) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(account);
        transaction.commit();
    }

    public List<Account> findAll() {
        final TypedQuery<Account> account = entityManager.createQuery(
                "from Account",
                Account.class
        );

        return account.getResultList();
    }

    public Account findById(Long id) {
        return entityManager.find(Account.class, id);
    }

    public void update(Account account) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.merge(account);
        transaction.commit();
    }

    public void delete(final Long id) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        final Account account = findById(id);
        entityManager.remove(account);
        transaction.commit();
    }
}
