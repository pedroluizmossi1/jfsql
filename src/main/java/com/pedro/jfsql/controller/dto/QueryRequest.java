package com.pedro.jfsql.controller.dto;


import java.util.List;

import com.pedro.jfsql.model.QueryParameter;
import jakarta.persistence.Parameter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class QueryRequest {
    private String query;
    private List<QueryParameter> parameters;

}