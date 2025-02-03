package io.github.miguel.eventostec.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Eventos-api",
                version = "v1",
                description = "Este projeto visa o desenvolvimento do backend de uma aplicação voltada para a gestão de Eventos. A aplicação permite a criação, listagem, filtragem e detalhamento de eventos, além da associação de cupons de desconto. O sistema contempla tanto eventos presenciais quanto remotos, com funcionalidades de consulta e gerenciamento de dados.",
                contact = @Contact(
                        name = "Miguel Pevidor",
                        email = "mpevidorcruz@gmail.com",
                        url = "https://github.com/MiguelPevidor"
                )
        )
)
public class OpenApiConfiguration {
}
