package com.pedro.jfsql.util;

import com.pedro.jfsql.handler.EndpointHandler;
import com.pedro.jfsql.model.Endpoint;
import com.pedro.jfsql.model.Query;
import com.pedro.jfsql.model.QueryParameter;
import com.pedro.jfsql.repository.EndpointRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    private final EndpointHandler endpointHandler;

    @Autowired
    public OpenApiConfig(EndpointHandler endpointHandler) {
        this.endpointHandler = endpointHandler;
    }

@Bean
public OpenAPI customOpenAPI() {
    OpenAPI openAPI = new OpenAPI();
    openAPI.setInfo(new io.swagger.v3.oas.models.info.Info().title("JFSQL API").version("1.0.0"));
    Paths paths = new Paths();

    List<Endpoint> endpoints = endpointHandler.findAllEndpoints();
    for (Endpoint endpoint : endpoints) {
        PathItem pathItem = new PathItem();
        Operation operation = new Operation();
        operation.setSummary(endpoint.getDescription());
        operation.setDescription(endpoint.getDescription());
        operation.setResponses(new ApiResponses().addApiResponse("200", new ApiResponse().description("Success")));
        operation.addTagsItem("Generated Endpoints");
        pathItem.setGet(operation);
        paths.addPathItem(endpoint.getEndpoint(), pathItem);
    }

    openAPI.setPaths(paths);
    return openAPI;
}
}