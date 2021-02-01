package com.oauth.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerDocumentationConfig {

    @Bean
    public Docket api() {

        List<Parameter> paramList = new ArrayList<>();

        Set<String> produceSet = new HashSet<>();
        produceSet.add(MediaType.APPLICATION_JSON.toString());
        List<ResponseMessage> defaultResponses = new ArrayList<>(3);
        defaultResponses.add(new ResponseMessage(400, "Bad or missing parameter", null, Collections.emptyMap(), Collections.emptyList()));
        defaultResponses.add(new ResponseMessage(401, "Failed to Authenticate",null, Collections.emptyMap(), Collections.emptyList()));
        defaultResponses.add(new ResponseMessage(403, "Forbidden or Wrong Password",null, Collections.emptyMap(), Collections.emptyList()));
        defaultResponses.add(new ResponseMessage(500, "Unexpected error occured",null, Collections.emptyMap(), Collections.emptyList()));

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, defaultResponses)
                .globalResponseMessage(RequestMethod.GET, defaultResponses)
                .globalResponseMessage(RequestMethod.DELETE, defaultResponses)
                .globalResponseMessage(RequestMethod.PUT, defaultResponses)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.oauth.login"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(paramList)
                .pathMapping("/")
                .produces(new HashSet<>(Arrays.asList(MediaType.APPLICATION_JSON.toString())))
                .consumes(new HashSet<>(Arrays.asList(MediaType.APPLICATION_JSON.toString())))
                .protocols(new HashSet<>(Arrays.asList("https")))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Oauth Login REST Service",
                "These API endpoints are used to Get Oauth Tokens and Login Sessions",
                "1.0",
                "",
                new Contact("Cerebri Test", "", ""),
                "",
                "",
                Collections.emptyList());
    }
}
