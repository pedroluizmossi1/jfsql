// Create a response object class
package com.pedro.jfsql.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ConnectionStatusResponse {
    private Long id;
    private String message;

    public ConnectionStatusResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }

}