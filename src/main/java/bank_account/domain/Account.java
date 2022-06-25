package bank_account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Account {
    private final long id;

    // Amount in cents (100 = 1€)
    private long amount;

    private final List<Operation> operations;

    public Account() {
        this.id = -1;
        this.amount = 0;
        this.operations = new ArrayList<>();
    }

    public void addAmount(long amount) {
        this.amount += amount;
    }

}
