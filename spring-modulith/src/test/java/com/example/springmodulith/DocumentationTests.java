package com.example.springmodulith;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class DocumentationTests {
    ApplicationModules modules = ApplicationModules.of(SpringModulithApplication.class);

    @Test
    void writeDocumentationSnippets() {

        Documenter documenter = new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }

}
