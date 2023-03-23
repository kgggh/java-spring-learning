package com.example.springmodulith;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApplicationTests {
    ApplicationModules modules = ApplicationModules.of(SpringModulithApplication.class);

    @Test
    void verifiesModularStructure() {
        ApplicationModules list = modules.verify();
        assertNotNull(list);
    }

    @Test
    void createModuleDocumentation() {
        new Documenter(modules).writeDocumentation();
    }

    @Test
    void modules() {
        modules.forEach(System.out::println);
    }
}
