package application;

import dao.AccountDAO;
import entities.Account;
import entities.enums.TypeAccount;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bank");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("------------------------------------------");
        final AccountDAO accountDAO= new AccountDAO(entityManager);

        //Create
        accountDAO.insert(new Account("Diogo2", 2000.00, TypeAccount.valueOf("CHECKIG_ACCOUNT")));

        //Read All
        System.out.println(accountDAO.findAll());

        //Read Id
        //System.out.println(accountDAO.findById(4L));

        //Update
        final Account account = accountDAO.findById(8L);
        account.withdraw(200.00);

        accountDAO.update(account);

        //Delete
    }
}
