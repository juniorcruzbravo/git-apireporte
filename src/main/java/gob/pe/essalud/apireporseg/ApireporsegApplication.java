package gob.pe.essalud.apireporseg;

import gob.pe.essalud.apireporseg.util.UsuarioOspeProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
@EnableConfigurationProperties({
		UsuarioOspeProperties.class
})



public class ApireporsegApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApireporsegApplication.class, args);
	}
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
}
