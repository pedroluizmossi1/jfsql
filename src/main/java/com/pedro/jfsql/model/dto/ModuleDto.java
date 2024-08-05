package com.pedro.jfsql.model.dto;

import com.pedro.jfsql.model.Module;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Module}
 */
@Value
public class ModuleDto implements Serializable {
    String name;
    String description;
    Boolean active;
}