package bank_account.service;

import bank_account.common.exception.AccountNotFoundException;
import bank_account.common.exception.IllegalAmountException;
import bank_account.domain.Account;
import bank_account.domain.Operation;
import bank_account.port.out.AccountLoader;
import bank_account.port.out.AccountSaver;
import bank_account.port.out.OperationSaver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class OperationServiceTest {

    @MockBean
    private AccountLoader accountLoaderMock;
    @MockBean
    private AccountSaver accountSaver;
    @MockBean
    private OperationSaver operationSaver;

    private OperationService operationService;

    @BeforeEach
    void init() {
        this.operationService = new OperationService(accountLoaderMock, operationSaver);
    }

    @Test
    void deposit() {
        Account accout = new Account(35, 100, null);
        when(accountLoaderMock.get(35)).thenReturn(accout);

        Account accoutAfterDeposit = new Account(35, 42100, null);
        Operation operation = new Operation(accoutAfterDeposit, 42000, LocalDateTime.now());
        when(operationSaver.save(new Operation(accoutAfterDeposit, 42000, any()))).thenReturn(operation);
        assertEquals(42100, operationService.deposit(35, 42000));
    }

    @Test
    void illegalDeposit() {
        assertThrows(IllegalAmountException.class, () -> operationService.deposit(12345, -29));
    }

    @Test
    void depositToUnknownAccount() {
        assertThrows(AccountNotFoundException.class, () -> operationService.deposit(12345, 56));
    }

    @Test
    void withdraw() {
        Account accout = new Account(35, 100, null);
        when(accountLoaderMock.get(35)).thenReturn(accout);

        Account accoutAfterWithdraw = new Account(35, 50, null);
        Operation operation = new Operation(accoutAfterWithdraw, -50, LocalDateTime.now());
        when(operationSaver.save(new Operation(accoutAfterWithdraw, -50, any()))).thenReturn(operation);
        assertEquals(50, operationService.withdraw(35, 50));
    }

    @Test
    void illegalWithdral() {
        Account accout = new Account(35, 100, null);
        when(accountLoaderMock.get(35)).thenReturn(accout);
        assertThrows(IllegalAmountException.class, () -> operationService.withdraw(35, 200));
    }

}