package bank_account.adapter.out.persistence;

import bank_account.adapter.out.persistence.data.mapping.AccountMapper;
import bank_account.adapter.out.persistence.data.mapping.OperationMapper;
import bank_account.domain.Account;
import bank_account.domain.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import({OperationPersistenceAdapter.class, OperationMapper.class, AccountMapper.class})
class OperationPersistenceAdapterTest {

    @Autowired
    OperationPersistenceAdapter operationPersistenceAdapter;

    @Test
    void save() {
        Account account = new Account(56, 9000);
        Operation operation = new Operation(account, 1000, LocalDateTime.now());
        assertEquals(operation, operationPersistenceAdapter.save(operation));
    }

}