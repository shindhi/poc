import dao.AccountDAO;
import entities.Account;
import entities.enums.TypeAccount;
import services.AccountServices;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bank");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("------------------------------------------");
        final AccountDAO accountDAO= new AccountDAO(entityManager);
        AccountServices accountServices = new AccountServices(accountDAO);

        //Create
        //accountDAO.insert(new Account("Diogo", 2000.00, TypeAccount.CHECKIG_ACCOUNT));

        //Search All
        //System.out.println(accountDAO.findAll());

        //Search Id
        //System.out.println(accountDAO.findById(4L));

        //Find By Id
        final Account account = accountDAO.findById(8L);

        //Deposit
        //account.deposit(400.00);
        accountServices.withdraw(account, amount);


        //Withdraw
        //account.withdraw(499.00);

        //accountDAO.update(account);

        //Delete
        //accountDAO.delete(7L);
    }
}
