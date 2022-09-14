package oop.fun.utils.diff.model;

import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Endpoint {

    private String pathUrl;
    private HttpMethod method;
    private String summary;
    private Path path;
    private Operation operation;

}
