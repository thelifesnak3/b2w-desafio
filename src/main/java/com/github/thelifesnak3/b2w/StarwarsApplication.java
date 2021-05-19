package com.github.thelifesnak3.b2w;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title="Starwars",
                version = "1.0.0",
                description = "Coleção de serviços da api de Starwars."
        )
)
public class StarwarsApplication {
}
