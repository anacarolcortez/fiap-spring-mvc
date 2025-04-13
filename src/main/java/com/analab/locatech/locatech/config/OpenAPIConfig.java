package com.analab.locatech.locatech.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI locatech(){
        return new OpenAPI()
                .info(new Info()
                        .title("LocaTech API")
                        .description("Projeto desenvolvido no módulo Spring MVC da pós-tech Arquitetura Java da FIAP")
                        .version("1.0")
                        .license(new License().name("Appache 2.0").url("https://github.com/anacarolcortez/fiap-spring-mvc"))
                );
    }
}
