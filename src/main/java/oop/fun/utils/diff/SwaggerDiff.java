package oop.fun.utils.diff;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import oop.fun.utils.diff.compare.SpecificationDiff;
import oop.fun.utils.diff.model.ChangedEndpoint;
import oop.fun.utils.diff.model.Endpoint;

import java.util.List;

@Setter
@Getter
@Slf4j
public class SwaggerDiff {


    private Swagger oldSpecSwagger;
    private Swagger newSpecSwagger;

    private List<Endpoint> newEndpoints;
    private List<Endpoint> missingEndpoints;
    private List<ChangedEndpoint> changedEndpoints;


    public static SwaggerDiff compare(String oldSpec, String newSpec) {
        return new SwaggerDiff(oldSpec, newSpec).compare();
    }


    /**
     * @param rawOldSpec
     * @param rawNewSpec
     */
    private SwaggerDiff(String rawOldSpec, String rawNewSpec) {
        SwaggerParser swaggerParser = new SwaggerParser();
        oldSpecSwagger = swaggerParser.parse(rawOldSpec);
        newSpecSwagger = swaggerParser.parse(rawNewSpec);
    }

    private SwaggerDiff compare() {
        SpecificationDiff diff = SpecificationDiff.diff(oldSpecSwagger, newSpecSwagger);
        this.newEndpoints = diff.getNewEndpoints();
        this.missingEndpoints = diff.getMissingEndpoints();
        this.changedEndpoints = diff.getChangedEndpoints();
        return this;
    }



}
