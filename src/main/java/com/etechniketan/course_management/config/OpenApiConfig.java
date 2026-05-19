package com.etechniketan.course_management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("My Course Management REST API")
//                        .version("1.0")
//                        .description("This is the description of my Spring Boot API project." +
//                                " It involves managing courses and students. You can use this " +
//                                "API to create, read, update, and delete courses and students. " +
//                                "It also includes authentication and authorization features."));
//    }

    @Bean
    public OpenAPI customOpenAPI() {

        // name to reflect basic authentication
        final String securitySchemeName = "basicAuth";

        // 1. Define Contact Information
        Contact contact = new Contact()
                .name("API Support Team")
                .email("support@example.com")
                .url("https://example.com/support");

        // 2. Define License Info
        License license = new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0.html");

        // 3. Define Environment Servers (shows a dropdown in Swagger UI to test different envs)
        Server localServer = new Server().url("http://localhost:8099").description("Local Development");
        Server stagingServer = new Server().url("https://staging-api.example.com").description("Staging Environment");

        // 4. Combine everything into the OpenAPI object
        return new OpenAPI()
                .info(new Info()
                        .title("My Project REST API")
                        .version("1.0.0")
                        .description("Comprehensive API documentation for our Spring Boot Course management project.")
                        .contact(contact)
                        .license(license))
                .servers(List.of(localServer, stagingServer))

                // 1. Configure components to use HTTP Basic authentication
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic"))) // <-- Changed from "bearer" to "basic";

                // 2. Apply basicAuth requirements globally to all endpoints
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    }
}
