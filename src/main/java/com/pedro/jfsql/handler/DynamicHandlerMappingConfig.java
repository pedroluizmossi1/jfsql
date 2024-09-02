package com.pedro.jfsql.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedro.jfsql.model.Connection;
import com.pedro.jfsql.model.Endpoint;
import com.pedro.jfsql.model.Query;
import com.pedro.jfsql.repository.EndpointRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Configuration
public class DynamicHandlerMappingConfig {

    private final EndpointRepository endpointRepository;
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;
    private final GeneratedEndpointHandler generatedEndpointHandler;


    @Autowired
    public DynamicHandlerMappingConfig(
            EndpointRepository endpointRepository,
            RequestMappingHandlerMapping requestMappingHandlerMapping,
            GeneratedEndpointHandler generatedEndpointHandler) {
        this.endpointRepository = endpointRepository;
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
        this.generatedEndpointHandler = generatedEndpointHandler;
    }

    @PostConstruct
    public void init() {
        try {
            refreshMappings();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshMappings() throws NoSuchMethodException {
        // Remove all existing mappings
        requestMappingHandlerMapping.getHandlerMethods().forEach((key, value) -> {
            if (key.getPatternsCondition() != null && key.getPatternsCondition().getPatterns().stream().anyMatch(pattern -> pattern.equals("/generated/**"))) {
                requestMappingHandlerMapping.unregisterMapping(key);
            }
        });

        // Reload endpoints from the database
        List<Endpoint> endpoints = endpointRepository.findAll();

        for (Endpoint endpoint : endpoints) {
            String path = endpoint.getEndpoint();
            String httpMethod = endpoint.getMethod();

            // Verifica se o método HTTP é válido
            RequestMethod methodType;
            try {
                methodType = RequestMethod.valueOf(httpMethod);
            } catch (IllegalArgumentException e) {
                System.err.println("Método HTTP inválido: " + httpMethod);
                continue; // Pular este endpoint se o método HTTP for inválido
            }

            Method method = DynamicHandlerMappingConfig.class.getMethod("handleDynamicEndpoint", HttpServletRequest.class, HttpServletResponse.class);

            // Cria um objeto RequestMappingInfo
            RequestMappingInfo requestMappingInfo = RequestMappingInfo
                    .paths(path)
                    .methods(methodType)
                    .build();

            // Adiciona o mapeamento ao RequestMappingHandlerMapping
            requestMappingHandlerMapping.registerMapping(requestMappingInfo, this, method);
        }
    }

    public void handleDynamicEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String endpoint = request.getRequestURI();
        Optional<Endpoint> endpointData = generatedEndpointHandler.getEndpointData(endpoint);

        if (endpointData.isPresent()) {
            List<Map<String, Object>> result = generatedEndpointHandler.processEndpoint(endpointData.get());
            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(result));
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
