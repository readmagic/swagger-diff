package oop.fun.utils.diff.model;

import io.swagger.models.properties.Property;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ElProperty {

    private String el;
    private Property property;
    private boolean isTypeChange;
    private boolean newEnums;
    private boolean removedEnums;

}
