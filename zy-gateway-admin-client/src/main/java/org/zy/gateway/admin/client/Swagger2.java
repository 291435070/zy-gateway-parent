package org.zy.gateway.admin.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// 与启动类同级目录
// 管理界面:http://127.0.0.1:8000/doc.html或http://127.0.0.1:8080/swagger-ui.html
@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("演示管理").select()
				// 监控的包路径
				.apis(RequestHandlerSelectors.basePackage("org.zy.gateway.admin.client.webs")).paths(PathSelectors.any())
				.build();
	}

	@Bean
	public Docket createWebRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("测试模块").select()
				// 监控的包路径
				.apis(RequestHandlerSelectors.basePackage("org.zy.gateway.admin.client.web"))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 标题
				.title("SpringBoot应用Swagger2构建RESTful API")
				// 作者
				.contact(new Contact("James L.G.", "https://www.baidu.com", "459360899@qq.com"))
				// 版本
				.version("1.0")
				// 描述
				.description("API描述").build();
	}

}