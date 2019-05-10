package es.cjolalla.catalogingredients;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

//Equivale a las anotaciones
//@Configure
//@AutoConfiguration
//@ComponentScan
//Como esta incluido el starter-web tambien se incluye la siguiente anotacion
//@EnableWebMvc
//Excluimos el SecurityAutoConfiguration para poder configurar nosotros la parte de seguridad
//@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class) //Esto permite que funcione envers
@SpringBootApplication
public class CatalogIngredientsApplication {
	
	@Bean
	public SpringSecurityDialect securityDialect() {
	    return new SpringSecurityDialect();
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(CatalogIngredientsApplication.class, args);
		//SpringApplication.run(CatalogIngredientsApplication.class, "--debug");
	}

}
