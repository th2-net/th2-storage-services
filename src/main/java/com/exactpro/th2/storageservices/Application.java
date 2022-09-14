package com.exactpro.th2.storageservices;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "TH2 Cradle Services",
                version = "0.1.0",
                description = "Informational REST API for cradle management data"
        )
)
public class Application {
    public static void main(String[] args) {
        Micronaut
                .build(args)
                .eagerInitConfiguration(true)
                .eagerInitSingletons(true)
                .mainClass(Application.class)
                .start();
    }
}
