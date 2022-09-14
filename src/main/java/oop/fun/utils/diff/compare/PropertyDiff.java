package oop.fun.utils.diff.compare;

import io.swagger.models.Model;
import io.swagger.models.properties.Property;
import oop.fun.utils.diff.model.ElProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PropertyDiff {

    private List<ElProperty> increased;
    private List<ElProperty> missing;
    private List<ElProperty> changed;

    Map<String, Model> oldDedinitions;
    Map<String, Model> newDedinitions;

    private PropertyDiff() {
        increased = new ArrayList<>();
        missing = new ArrayList<>();
        changed = new ArrayList<>();
    }

    public static PropertyDiff buildWithDefinition(Map<String, Model> left, Map<String, Model> right) {
        PropertyDiff diff = new PropertyDiff();
        diff.oldDedinitions = left;
        diff.newDedinitions = right;
        return diff;
    }

    public PropertyDiff diff(Property left, Property right) {
        ModelDiff diff = ModelDiff.buildWithDefinition(oldDedinitions, newDedinitions).diff(left, right);
        increased.addAll(diff.getIncreased());
        missing.addAll(diff.getMissing());
        changed.addAll(diff.getChanged());
        return this;
    }

    public List<ElProperty> getIncreased() {
        return increased;
    }

    public void setIncreased(List<ElProperty> increased) {
        this.increased = increased;
    }

    public List<ElProperty> getMissing() {
        return missing;
    }

    public void setMissing(List<ElProperty> missing) {
        this.missing = missing;
    }

    public List<ElProperty> getChanged() {
        return changed;
    }

    public void setChanged(List<ElProperty> changed) {
        this.changed = changed;
    }
}
