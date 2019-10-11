package entities.enums;

import java.util.HashMap;
import java.util.Map;

public enum TypeAccount {
    CHECKIG_ACCOUNT("CHECKING", 500.0),
    SAVINGS_ACCOUNT("SAVING", 0.0);

    private static final Map<String, TypeAccount> BY_TYPE = new HashMap<String, TypeAccount>();
    private static final Map<Double, TypeAccount> BY_LIMIT = new HashMap<Double, TypeAccount>();

    static {
        for (TypeAccount a : values()) {
            BY_TYPE.put(a.typeAccount, a);
            BY_LIMIT.put(a.loanLimit, a);
        }
    }

    public final String typeAccount;
    public final Double loanLimit;

    private TypeAccount(String typeAccount, Double loanLimit) {
        this.typeAccount = typeAccount;
        this.loanLimit = loanLimit;
    }
}
