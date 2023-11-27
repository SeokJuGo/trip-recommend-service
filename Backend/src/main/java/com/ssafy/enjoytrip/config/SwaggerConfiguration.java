package com.ssafy.enjoytrip.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//@EnableSwagger2
public class SwaggerConfiguration {
	private static final String API_TITLE = "EnjoyTrip API";
	private static final String API_VERSION = "1.0.1";
	private static final String API_DESCRIPTION = ""
			+ "<h3>SSAFY API Reference for Developers</h3>"
			+ "Swagger를 이용한 EnjoyTrip API<br>";
//			+ "<img src=\\\"/assets/img/ssafy_logo.png\\\" width=\\\"150\\\">";

	// Swagger-UI 2.x 확인
	// http://localhost:8080/{your-app-root}/swagger-ui.html
	// Swagger-UI 3.x 확인
	// http://localhost:8080/{your-app-root}/swagger-ui/index.html
	
	private static final String API_TYPES = "home|user|admin|sido|gugun|attraction|board";
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.consumes(getConsumeContentTypes())
				.produces(getProduceContentTypes())
				.useDefaultResponseMessages(false)
				.groupName(API_VERSION).select()
//				.apis(RequestHandlerSelectors.basePackage("com.ssafy.enjoytrip.attraction.controller"))
				.apis(RequestHandlerSelectors.any())		// @RequestMapping 으로 할당된 모든 URL 리스트 추출
				.paths(PathSelectors.regex("/.*"))			// regex()를 사용할 때는 "/{path}.*"
//				.paths(PathSelectors.ant("/**"))			// ant()를 사용할 때는 "/{path}/**"
//				.paths(PathSelectors.regex("/enjoytrip.*"))	// application.properties의 context-path를 포함한 API URL
//				.paths(PathSelectors.ant("/enjoytrip/**"))	// application.properties의 context-path를 포함한 API URL
//				.paths(PathSelectors.regex("/enjoytrip/(" + API_TYPES + ").*"))	// 필요한 API만 Swagger에 표시
				.build();
	}

	private Set<String> getConsumeContentTypes() {
		Set<String> consumes = new HashSet<>();
//      consumes.add("application/xml;charset=UTF-8");
		consumes.add("application/json;charset=UTF-8");
		consumes.add("application/x-www-form-urlencoded");
		return consumes;
	}

	private Set<String> getProduceContentTypes() {
		Set<String> produces = new HashSet<>();
		produces.add("application/json;charset=UTF-8");
		return produces;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(API_TITLE)
				.version(API_VERSION)
				.description(API_DESCRIPTION)
				.license("SSAFY License")
				.licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com"))
				.build();
	}

}