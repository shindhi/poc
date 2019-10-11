package entities;

import dao.AccountDAO;
import entities.enums.TypeAccount;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.lang.model.element.TypeElement;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "holder", nullable = false)
    private String holder;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_account")
    private TypeAccount typeAccount;

    @Column(name = "loan_limit", nullable = true)
    private Double loanLimit;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


    public Account() {}

    public Account(final String holder, final double balance, final TypeAccount typeAccount) {
        this.holder = holder;
        this.balance = balance;
        this.typeAccount = typeAccount;
        this.loanLimit = typeAccount.loanLimit;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "\n[\n", "\n]")
                .add("id=" + id)
                .add("holder = '" + holder + "'")
                .add("balance = " + balance)
                .add("typeAccount = " + typeAccount)
                .add("createdAt = " + createdAt)
                .add("updatedAt = " + updatedAt)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(TypeAccount typeAccount) {
        this.typeAccount = typeAccount;
    }

    public Double getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(Double loanLimit) {
        this.loanLimit = loanLimit;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void deposit(double value) {
        this.balance = getBalance() + value;
    }

    public void withdraw(double value) {
        if (typeAccount.equals("CHECKING")) {
            if (getBalance() + value <= this.loanLimit) {
                this.balance = getBalance() - value;
            }
            System.out.println("You have exceeded your limit");
        } else {
            this.balance = getBalance() - value;
        }
    }
}
