package bank_account.domain;

import bank_account.common.exception.IllegalAmountException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Account {
    private final long id;

    // Amount in cents (100 = 1€)
    private long amount;


    public Account() {
        this.id = -1;
        this.amount = 0;
    }

    public void addAmount(long amount) {
        if (this.amount + amount < 0) {
            throw new IllegalAmountException("Withdrawal amount is bigger than account amount");
        }
        this.amount += amount;
    }
}
