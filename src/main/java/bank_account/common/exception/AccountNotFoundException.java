package bank_account.common.exception;

import java.util.NoSuchElementException;

public class AccountNotFoundException extends NoSuchElementException {

    public AccountNotFoundException(String message) {
        super(message);
    }

}
