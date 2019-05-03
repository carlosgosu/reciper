package es.cjolalla.catalogingredients.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("es.cjolalla.catalogingredients.usuarios")
@Profile("!test") //Con esto no cargamos esto si esta el perfil test activo (el problema es que el EnalbeAutoConfiguration de spring boot esta cargando igualmente spring security)
public class SecurityCatalogConfig extends WebSecurityConfigurerAdapter{
	
	//Aqui no se puede hacer inyeccion por constructor  No visible constructors in class es.cjolalla.catalogingredients.security.SecurityCatalogConfig
	
	//La clase concreta en vez de la interfaz? No me deja, tengo que instanciarlo como la interfaz
	@Autowired
	private UserDetailsService usuarioService;

	
//	private final AuthenticationEntryPoint entryPoint;
//	
//	
//	@Autowired
//	public SecurityCatalogConfig(AuthenticationEntryPoint ep) {
//		super();
//		this.entryPoint = ep;
//		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
//	}
//	
//	@Override
//	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//	    auth.inMemoryAuthentication()
//	        .withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN")
//	        .and()
//	        .withUser("user").password(encoder().encode("userPass")).roles("USER");
//	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(usuarioService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}
	 
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
		http
        .csrf().disable()
        .authorizeRequests()
        	//Spring Boot will, by default, permit access to /css/**, /js/**, /images/**, and /**/favicon.ico
        	.antMatchers("/console", "/console/**", "/info","/httptrace", "/").permitAll() 
        	.anyRequest().authenticated()
        .and()
        .headers().frameOptions().disable() //Esta linea hace falta para que se muestre la consla de h2 /console si esta configurada en el propoerties
        .and()
        .httpBasic() //permitir autenticacion basica
        .and()
        .formLogin() //permitir autenticacion con formulario tambien (el index tendra un formulario y la accion que autenticara sera perfom_login)
        	.loginPage("/index.html")
        	.permitAll()
        	.loginProcessingUrl("/perform_login") //Por defecto la url de spring boot para esto es /login. La sobreescribimos
        	.defaultSuccessUrl("/", false); //Si pusiera true de segundo parametro iria siempre a esta pagina despues de autenticarse aunque estuvieran intentando acceder a otra 
		
//	    http
//	    .csrf().disable()
//	    .exceptionHandling()
//	    //.authenticationEntryPoint(entryPoint)
//	    .and()
//	    .authorizeRequests()
//	    .antMatchers("/ingredientes/**").authenticated()
//	    .antMatchers("/ingredientes/admin/**").hasRole("ADMIN")
//	    .and()
//	    .formLogin()
//	    //.successHandler(succesHandler)
//	    .and()
//	    .logout();
	}

}
