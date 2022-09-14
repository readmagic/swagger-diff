package oop.fun.utils.diff.model;

import com.google.common.collect.Lists;
import io.swagger.models.parameters.Parameter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ChangedOperation implements Changed {

    private String summary;

    private List<Parameter> addParameters = Lists.newArrayList();
    private List<Parameter> missingParameters = Lists.newArrayList();

    private List<ChangedParameter> changedParameter = Lists.newArrayList();

    private List<ElProperty> addProps = Lists.newArrayList();
    private List<ElProperty> missingProps = Lists.newArrayList();
    private List<ElProperty> changedProps = Lists.newArrayList();
    private List<String> addConsumes = Lists.newArrayList();
    private List<String> missingConsumes = Lists.newArrayList();
    private List<String> addProduces = Lists.newArrayList();
    private List<String> missingProduces = Lists.newArrayList();


    @Override
    public boolean isDiff() {
        return !addParameters.isEmpty() || !missingParameters.isEmpty() || !changedParameter.isEmpty() || isDiffProp()
                || isDiffConsumes() || isDiffProduces();
    }

    public boolean isDiffProp() {
        return !addProps.isEmpty() || !missingProps.isEmpty() || !changedProps.isEmpty();
    }

    public boolean isDiffConsumes() {
        return !addConsumes.isEmpty() || !missingConsumes.isEmpty();
    }

    public boolean isDiffProduces() {
        return !addProduces.isEmpty() || !missingProduces.isEmpty();
    }

    public boolean isDiffParam() {
            return !addParameters.isEmpty() || !missingParameters.isEmpty() || !changedParameter.isEmpty();
        }

}
