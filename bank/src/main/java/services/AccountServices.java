package services;

import dao.AccountDAO;
import entities.Account;

public class AccountServices {

    private double amount;

    AccountDAO accountDAO;
    Account account;
    AccountLimit accountLimit;

    public AccountServices(double amount, Account account, AccountDAO accountDAO) {
        this.amount = amount;
        this.account = account;
        this.accountDAO = accountDAO;

        this.withdraw();
    }

    /**
     * Saque
     */


    /**
     * Deposito
     */
    public void withdraw() {
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
