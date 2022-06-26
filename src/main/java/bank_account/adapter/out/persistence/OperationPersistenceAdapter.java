package bank_account.adapter.out.persistence;

import bank_account.adapter.out.persistence.data.OperationEntity;
import bank_account.adapter.out.persistence.data.mapping.OperationMapper;
import bank_account.domain.Operation;
import bank_account.port.out.OperationLoader;
import bank_account.port.out.OperationSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OperationPersistenceAdapter implements OperationLoader, OperationSaver {

    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    @Override
    public Operation save(Operation operation) {
        OperationEntity operationEntity = operationMapper.mapDomainToEntity(operation);
        operationEntity = operationRepository.save(operationEntity);
        return operationMapper.mapEntityToDomain(operationEntity);
    }

    @Override
    public List<Operation> getAll(long accountId) {
        return operationRepository.findAllByAccountIdOrderByDateDesc(accountId)
                .stream()
                .map(operationMapper::mapEntityToDomain)
                .toList();
    }
}
