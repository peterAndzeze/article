package com.article.recommend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
@SpringBootApplication
public class ArticlerecommendApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder){
		return  springApplicationBuilder.sources(ArticlerecommendApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ArticlerecommendApplication.class, args);
	}
}
