// Create a response object class
package com.pedro.jfsql.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ModuleStatusResponse {
    private String name;
    private Boolean active;
    private String message;

    public ModuleStatusResponse(String name, Boolean active, String message) {
        this.name = name;
        this.active = active;
        this.message = message;
    }

}