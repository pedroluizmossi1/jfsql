package com.pedro.jfsql.handler;

import com.pedro.jfsql.model.Endpoint;
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

@Configuration
public class DynamicHandlerMappingConfig {

    @Autowired
    private EndpointRepository endpointRepository;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @PostConstruct
    public void init() {
        try {
            refreshMappings();
        } catch (Exception e) {
            e.printStackTrace();
            // Adicione tratamento de exceção apropriado aqui
        }
    }

    public void refreshMappings() throws NoSuchMethodException {
        // Remove all existing mappings
        //requestMappingHandlerMapping.getHandlerMethods().clear();

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

            RequestMappingInfo requestMappingInfo = RequestMappingInfo.paths(path).methods(methodType).build();
            requestMappingHandlerMapping.registerMapping(requestMappingInfo, this, method);
        }
    }

    public void handleDynamicEndpoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Handle the dynamic endpoint logic
        response.getWriter().write("Dynamic Endpoint");
    }
}
