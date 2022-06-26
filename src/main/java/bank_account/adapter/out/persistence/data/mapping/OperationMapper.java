package bank_account.adapter.out.persistence.data.mapping;

import bank_account.adapter.out.persistence.AccountRepository;
import bank_account.adapter.out.persistence.data.AccountEntity;
import bank_account.adapter.out.persistence.data.OperationEntity;
import bank_account.domain.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OperationMapper {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    public Operation mapEntityToDomain(OperationEntity operationEntity) {
        return new Operation(accountMapper.mapEntityToDomainWithoutOperations(operationEntity.getAccount()),
                operationEntity.getAmount(),
                operationEntity.getDate());
    }

    public OperationEntity mapDomainToEntity(Operation operation) {
        Optional<AccountEntity> optionalAccountEntity = accountRepository.findById(operation.account().getId());
        if (optionalAccountEntity.isPresent()) {
            AccountEntity accountEntity = optionalAccountEntity.get();
            accountMapper.merge(operation.account(), accountEntity);

            OperationEntity operationEntity = new OperationEntity();
            operationEntity.setAccount(accountEntity);
            operationEntity.setAmount(operation.amount());
            operationEntity.setDate(operation.timestamp());
            return operationEntity;
        }
        throw new IllegalArgumentException("Operation should have an existing account");
    }

}
