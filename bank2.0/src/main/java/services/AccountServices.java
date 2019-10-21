package services;

import dao.AccountDAO;
import entities.Account;

public class AccountServices {

    private double amount;

    AccountDAO accountDAO;
    Account account;
    AccountLimit accountLimit;

    public AccountServices(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;

        this.withdraw();
    }

    /**
     * Saque
     */


    /**
     * Deposito
     */
    public void withdraw(amount) {
        double balance = account.getBalance();

        account.setBalance(balance + amount);
        accountDAO.update(account);
    }

    /**
     * Extrato
     */


    /**
     * Cheque especial
     */


}
