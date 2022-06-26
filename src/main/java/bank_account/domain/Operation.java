package bank_account.domain;

import java.time.LocalDateTime;

/**
 * Represents an account operation such as a deposit or a withdrawal
 *
 * @param account   account related to the operation
 * @param amount    amount to add to the account (negative in case of a withdrawal)
 * @param timestamp date of the operation
 */
public record Operation(Account account, long amount, LocalDateTime timestamp) {

    /**
     * Apply the operation. Should be called at most once.
     */
    public void apply() {
        this.account.addAmount(amount);
    }

}