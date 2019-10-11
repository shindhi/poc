package application;

import dao.AccountDAO;
import entities.Account;
import entities.enums.TypeAccount;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bank");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("------------------------------------------");
        final AccountDAO accountDAO= new AccountDAO(entityManager);

        //Create
        //accountDAO.insert(new Account("Diogo2", 2000.00, TypeAccount.valueOf("CHECKIG_ACCOUNT")));

        //Search All
        System.out.println(accountDAO.findAll());

        //Search Id
        //System.out.println(accountDAO.findById(4L));

        //Update
        final Account account = accountDAO.findById(8L);

        //Deposit
        account.deposit(400.00);

        //Withdraw
        final double limit = 500.00;
        //account.withdraw(499.00, limit);

        accountDAO.update(account);

        //Delete
        //accountDAO.delete(7L);
    }
}
