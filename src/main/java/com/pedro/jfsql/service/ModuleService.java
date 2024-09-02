package com.pedro.jfsql.service;

import com.pedro.jfsql.exception.ModuleExceptions;
import com.pedro.jfsql.model.dto.ModuleDto;
import com.pedro.jfsql.repository.ModuleRepository;
import com.pedro.jfsql.model.Module;
import com.pedro.jfsql.util.I18n;
import com.pedro.jfsql.util.I18nMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ModuleService {

    private final ModuleRepository moduleRepository;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public List<Module> findAllModules() {
        return moduleRepository.findAll();
    }

    public Module findModuleById(String name) {
        return moduleRepository.findByName(name)
                .orElseThrow(() -> new ModuleExceptions.ModuleNotFoundException(name));
    }

    public Module createModule(ModuleDto module) {
        if (moduleRepository.findByName(module.getName()).isPresent()) {
            throw new ModuleExceptions.ModuleAlreadyExistsException(module.getName());
        }
        return moduleRepository.save(new Module(module.getName(), module.getDescription(), module.getActive()));
    }

    public void deleteModule(String name) {
        moduleRepository.deleteByName(name);
    }

    public void updateModule(String name, Module module) {
        Module moduleToUpdate = moduleRepository.findByName(name)
                .orElseThrow(() -> new ModuleExceptions.ModuleNotFoundException(name));
        moduleToUpdate.setName(module.getName());
        moduleToUpdate.setDescription(module.getDescription());
        moduleToUpdate.setActive(module.getActive());
        moduleRepository.save(moduleToUpdate);
    }

    public Boolean updateModuleActive(String name) {
        Module module = moduleRepository.findByName(name)
                .orElseThrow(() -> new ModuleExceptions.ModuleNotFoundException(name));
        module.setActive(!module.getActive());
        moduleRepository.save(module);
        return module.getActive();
    }



}
