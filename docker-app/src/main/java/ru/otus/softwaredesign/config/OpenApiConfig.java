package ru.otus.softwaredesign.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "Otus UserAPI",
        version = "1.0",
        description = "My API",
        license = @License(name = "Apache 2.0", url = "http://foo.bar")
    ),
    tags = {
    },
    externalDocs = @ExternalDocumentation(description = "definition docs desc"),
    security = {
    },
    servers = {
        @Server(
            description = "server 1",
            url = "http://foo"
        )
    }
)
@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi usersOpenApi() {
        final String[] paths = {"/users/**"};
        return GroupedOpenApi.builder()
            .group("users")
            .pathsToMatch(paths)
            .build();
    }

    @Bean
    public GroupedOpenApi defaultOpenApi() {
        final String[] paths = {"/**"};
        return GroupedOpenApi.builder()
            .group("*")
            .pathsToMatch(paths)
            .build();
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry
            .config().commonTags("application", "app-docker");
    }
}
