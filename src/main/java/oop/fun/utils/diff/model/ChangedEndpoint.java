package oop.fun.utils.diff.model;

import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Setter
@Getter
public class ChangedEndpoint implements Changed {

    private String pathUrl;
    private Map<HttpMethod, Operation> newOperations;
    private Map<HttpMethod, Operation> missingOperations;
    private Map<HttpMethod, ChangedOperation> changedOperations;

    @Override
    public boolean isDiff() {
        return !changedOperations.isEmpty();
    }

}
