package bank_account.domain;

import java.time.LocalDateTime;

public record Operation(Account account, long amount, LocalDateTime timestamp) {
}