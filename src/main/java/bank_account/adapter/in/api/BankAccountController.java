package bank_account.adapter.in.api;

import bank_account.common.exception.AccountNotFoundException;
import bank_account.common.exception.IllegalAmountException;
import bank_account.domain.Account;
import bank_account.port.in.AccountManager;
import bank_account.port.in.DepositHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class BankAccountController {

    private final AccountManager accountManager;
    private final DepositHandler depositHandler;

    @PostMapping
    public ResponseEntity<Account> createAccount() {
        return new ResponseEntity<>(accountManager.create(), HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable("accountId") long accountId) {
        Account account = accountManager.get(accountId);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(account);
    }

    /**
     * Makes a deposit to a bank account
     *
     * @param accountId id of the account to make the deposit to
     * @param amount    amount to deposit to the account
     * @return new amount in the account with id `accountId`
     */
    @PostMapping("/{accountId}/deposit/{amount}")
    public ResponseEntity<Long> deposit(@PathVariable("accountId") long accountId, @PathVariable("amount") long amount) {
        try {
            return ResponseEntity.ok(this.depositHandler.deposit(accountId, amount));
        } catch (IllegalAmountException e) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}