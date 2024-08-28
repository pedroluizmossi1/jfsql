package com.pedro.jfsql.controller;

import com.pedro.jfsql.handler.GeneratedEndpointHandler;
import com.pedro.jfsql.model.Endpoint;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
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
    public void handleGeneratedEndpoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String endpoint = request.getRequestURI();
        Optional<Endpoint> endpointData = generatedEndpointHandler.getEndpointData(endpoint);

        if (endpointData.isPresent()) {
            String result = generatedEndpointHandler.processEndpoint(endpointData.get());
            response.getWriter().write(result);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Not Found");
        }
    }
}
