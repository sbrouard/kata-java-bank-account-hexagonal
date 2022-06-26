package bank_account.port.out;

import bank_account.domain.Operation;

public interface OperationSaver {

    Operation save(Operation operation);

}
