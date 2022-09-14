package oop.fun.utils.diff.model;

import com.google.common.collect.Lists;
import io.swagger.models.parameters.Parameter;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class ChangedParameter implements Changed {

    private List<ElProperty> increased = new ArrayList<ElProperty>();
    private List<ElProperty> missing = Lists.newArrayList();
    private List<ElProperty> changed = Lists.newArrayList();
    private Parameter leftParameter;
    private Parameter rightParameter;
    private boolean isChangeRequired;
    private boolean isChangeDescription;



    @Override
    public boolean isDiff() {
        return isChangeRequired || isChangeDescription || !increased.isEmpty() || !missing.isEmpty()
                || !changed.isEmpty();
    }


}
