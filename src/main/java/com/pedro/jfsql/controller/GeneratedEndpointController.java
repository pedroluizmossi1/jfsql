package com.pedro.jfsql.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedro.jfsql.handler.GeneratedEndpointHandler;
import com.pedro.jfsql.model.Endpoint;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class GeneratedEndpointController {


    private final GeneratedEndpointHandler generatedEndpointHandler;

    @Autowired
    public GeneratedEndpointController(GeneratedEndpointHandler generatedEndpointHandler) {
        this.generatedEndpointHandler = generatedEndpointHandler;
    }

    @RequestMapping("/generated/**")
    @Tag(name = "Generated Endpoints API", description = "API for generated endpoints")
    public void handleGeneratedEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
