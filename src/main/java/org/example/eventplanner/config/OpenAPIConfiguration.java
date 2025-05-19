package org.example.eventplanner.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Djordje Krstic");
        myContact.setEmail("krsticdjordje372@gmail.com");

        Info information = new Info()
                .title("Event Planner API")
                .version("1.0")
                .description("Contains default CRUD operations for Guest, Event and EventGuest classes and operations " +
                        "for searching via class fields (e.g. guest - email, event - theme), and operations for getting " +
                        "analytical data (e.g. getting top 5 guests who confirmed attendance and attended an event)")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
