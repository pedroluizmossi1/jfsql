package com.pedro.jfsql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "modules")
public class Module {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false)
    @JsonIgnore
    @CreationTimestamp
    private String created_at;

    @Column(name = "updated_at", nullable = false)
    @JsonIgnore
    @UpdateTimestamp
    private String updated_at;

    public Module() {
    }

    public Module(String name, String description, Boolean status) {
        this.name = name;
        this.description = description;
        this.active = status;
    }

}