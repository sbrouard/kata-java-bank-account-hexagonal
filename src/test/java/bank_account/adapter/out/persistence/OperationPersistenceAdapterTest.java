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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void getAll() {
        assertEquals(2, operationPersistenceAdapter.getAll(22).size());
    }

    @Test
    void getAllOrderedTest() {
        List<Operation> operationList = operationPersistenceAdapter.getAll(22);
        assertTrue(operationList.get(0).timestamp().isAfter(operationList.get(1).timestamp()));
    }

}