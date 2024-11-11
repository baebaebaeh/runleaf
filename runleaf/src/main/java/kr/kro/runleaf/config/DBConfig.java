package kr.kro.runleaf.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {
		"kr.kro.runleaf.repository"
})
public class DBConfig {

}
