package com.pedro.jfsql.handler;

import com.pedro.jfsql.controller.GeneratedEndpointController;
import com.pedro.jfsql.model.Endpoint;
import com.pedro.jfsql.repository.EndpointRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.support.InvocableHandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.List;

@Configuration
public class DynamicHandlerMappingConfig {

    @Autowired
    private EndpointRepository endpointRepository;

    @Autowired
    private GeneratedEndpointController generatedEndpointController;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @PostConstruct
    public void init() throws NoSuchMethodException {
        // Carrega os endpoints do banco de dados
        List<Endpoint> endpoints = endpointRepository.findAll();

        for (Endpoint endpoint : endpoints) {
            String path = endpoint.getEndpoint();
            String httpMethod = endpoint.getMethod();

            Method method = GeneratedEndpointController.class.getMethod("handleGeneratedEndpoint", HttpServletRequest.class, HttpServletResponse.class);
            InvocableHandlerMethod handlerMethod = new InvocableHandlerMethod(generatedEndpointController, method);

            requestMappingHandlerMapping.registerMapping(
                    RequestMappingInfo.paths(path).methods(RequestMethod.valueOf(httpMethod)).build(),
                    generatedEndpointController,
                    method
            );
        }
    }
}
