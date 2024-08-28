package com.pedro.jfsql.util;

import com.pedro.jfsql.model.Endpoint;
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

    private final EndpointRepository endpointRepository;

    @Autowired
    public OpenApiConfig(EndpointRepository endpointRepository) {
        this.endpointRepository = endpointRepository;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI();
        Paths paths = new Paths();

        List<Endpoint> endpoints = endpointRepository.findAll();

        for (Endpoint endpoint : endpoints) {
            PathItem pathItem = new PathItem();

            Operation operation = new Operation()
                    .summary("Dynamically generated endpoint")
                    .addParametersItem(new Parameter().in("query").name("example").description("Example parameter"))
                    .responses(new ApiResponses().addApiResponse("200", new ApiResponse().description("Success")));

            switch (endpoint.getMethod().toUpperCase()) {
                case "GET":
                    pathItem.get(operation);
                    break;
                case "POST":
                    pathItem.post(operation);
                    break;
                // Adicione outros métodos HTTP conforme necessário
            }

            paths.addPathItem(endpoint.getEndpoint(), pathItem);
        }

        openAPI.setPaths(paths);
        return openAPI;
    }
}