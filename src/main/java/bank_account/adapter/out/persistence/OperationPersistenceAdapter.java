package bank_account.adapter.out.persistence;

import bank_account.adapter.out.persistence.data.OperationEntity;
import bank_account.adapter.out.persistence.data.mapping.OperationMapper;
import bank_account.domain.Operation;
import bank_account.port.out.OperationSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OperationPersistenceAdapter implements OperationSaver {

    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    @Override
    public Operation save(Operation operation) {
        OperationEntity operationEntity = operationMapper.mapDomainToEntity(operation);
        operationEntity = operationRepository.save(operationEntity);
        return operationMapper.mapEntityToDomain(operationEntity);
    }
}
