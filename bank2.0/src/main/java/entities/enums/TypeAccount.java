package entities.enums;

import java.util.HashMap;
import java.util.Map;

public enum TypeAccount {
    CHECKIG_ACCOUNT("CHECKING", 500.0),
    SAVINGS_ACCOUNT("SAVING", 0.0);


    public final String typeAccount;
    public final Double loanLimit;

    private TypeAccount(String typeAccount, Double loanLimit) {
        this.typeAccount = typeAccount;
        this.loanLimit = loanLimit;
    }
}
