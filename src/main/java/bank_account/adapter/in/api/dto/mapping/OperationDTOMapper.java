package bank_account.adapter.in.api.dto.mapping;

import bank_account.adapter.in.api.dto.OperationDTO;
import bank_account.domain.Operation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OperationDTOMapper {

    public OperationDTO mapDomainToDTO(Operation operation) {
        return new OperationDTO(operation.amount(), operation.timestamp());
    }

    public List<OperationDTO> mapDomainListToDTOList(List<Operation> operationList) {
        return operationList.stream().map(this::mapDomainToDTO).toList();
    }

}
