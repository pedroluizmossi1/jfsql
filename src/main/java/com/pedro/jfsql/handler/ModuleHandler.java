package com.pedro.jfsql.handler;

import com.pedro.jfsql.controller.dto.ModuleStatusResponse;
import com.pedro.jfsql.model.Module;
import com.pedro.jfsql.model.dto.ModuleDto;
import com.pedro.jfsql.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModuleHandler {

    private final ModuleService moduleService;

    @Autowired
    public ModuleHandler(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    public void createModule(ModuleDto module) {
        moduleService.createModule(module);
    }

    public void deleteModule(String name) {
        moduleService.deleteModule(name);
    }

    public void updateModule(String name, Module module) {
        moduleService.updateModule(name, module);
    }

    public ModuleStatusResponse updateModuleActive(String name) {
        Boolean status = moduleService.updateModuleActive(name);
        String message = status ? "deactivated" : "activated";
        return new ModuleStatusResponse(name, !status, "Module " + name + " " + message);
    }

    public Module findModuleById(String name) {
        return moduleService.findModuleById(name);
    }


    public List<Module> findAllModules() {
        return moduleService.findAllModules();
    }
}