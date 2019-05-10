package es.cjolalla.catalogingredients.usuarios.web;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	//Tambien se puede utilizar RequestMapping
	@GetMapping(value= {"/","/index"})
	public String index(HttpSession session) {
		
		SecurityContext b = SecurityContextHolder.getContext();
		SecurityContext sec = (SecurityContext)session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		return "index";
	}
	
	@GetMapping(value= {"/autenticacion"})
	public String autenticacion(HttpSession session) {
		
		SecurityContext b = SecurityContextHolder.getContext();
		SecurityContext sec = (SecurityContext)session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		return "login";
	}
	
}
