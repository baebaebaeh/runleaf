package kr.kro.runleaf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/uploads/**")
	            .addResourceLocations("file:///");
	    registry.addResourceHandler("/icons/**")
	    		.addResourceLocations("file:///c:/SSAFY/assets/icons/");
	}
}
