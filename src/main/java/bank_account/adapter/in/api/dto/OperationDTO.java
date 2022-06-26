package bank_account.adapter.in.api.dto;

import java.time.LocalDateTime;

public record OperationDTO(long amount, LocalDateTime date) {
}
