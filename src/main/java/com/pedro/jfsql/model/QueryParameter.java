package com.pedro.jfsql.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Parameter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryParameter implements Parameter<Object> {
    private String name;
    private Integer position;
    private Object value;

    public QueryParameter() {
    }

    public QueryParameter(String name, Integer position, Object value) {
        this.name = name;
        this.position = position;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getPosition() {
        return position;
    }

    @Override
    public Class<Object> getParameterType() {
        return Object.class;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}