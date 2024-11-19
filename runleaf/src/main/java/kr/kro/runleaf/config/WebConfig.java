package kr.kro.runleaf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/uploads/**")
	            .addResourceLocations("file:///C:/SSAFY/");
//	    .addResourceLocations("file:///Users/baehanjin/SSAFY/");
	    registry.addResourceHandler("/icons/**")
	    		.addResourceLocations("file:///c:/SSAFY/assets/icons/");
//	    .addResourceLocations("file:///Users/baehanjin/SSAFY/assets/icons/");
	}
}