package kr.kro.runleaf.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {
		"kr.kro.runleaf.board.repository",
		"kr.kro.runleaf.member.repository"
})
public class DBConfig {

}
