package com.pedro.jfsql.controller;

import com.pedro.jfsql.controller.dto.ModuleStatusResponse;
import com.pedro.jfsql.handler.ModuleHandler;
import com.pedro.jfsql.model.Module;
import com.pedro.jfsql.model.dto.ModuleDto;
import com.pedro.jfsql.service.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modules")
@Tag(name = "Modules API", description = "API for managing modules")
public class ModuleController {

    private final ModuleHandler moduleHandler;

    @Autowired
    public ModuleController(ModuleService moduleService, ModuleHandler moduleHandler) {
        this.moduleHandler = moduleHandler;
    }

    @GetMapping
    @Operation(summary = "Get all Modules", description = "Get all modules")
    public List<Module> getAllModules() {
        return moduleHandler.findAllModules();
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get Module by name", description = "Get a module by its name")
    public Module getModuleById(@PathVariable String name) {
        return moduleHandler.findModuleById(name);
    }

    @PostMapping
    @Operation(summary = "Create Module", description = "Create a new module")
    public ModuleDto createModule(@RequestBody ModuleDto module) {
        moduleHandler.createModule(module);
        return module;
    }

    @PutMapping("/{name}")
    @Operation(summary = "Update Module", description = "Update a module by its name")
    public Module updateModule(@PathVariable String name, @RequestBody Module module ) {
        moduleHandler.updateModule(name, module);
        return module;
    }

    @PutMapping("/{name}/active")
    @Operation(summary = "Update Module active", description = "Update a module active status by its name")
    public ResponseEntity<ModuleStatusResponse> updateModuleActive(@PathVariable String name) {
        ModuleStatusResponse response = moduleHandler.updateModuleActive(name);
        return ResponseEntity.ok(response);
    }

}