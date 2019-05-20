package es.cjolalla.catalogingredients.usuarios.web;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Este controlador lo usa React para saber si un usuario esta autenticado y actuar en consecuencia
 * @author carlosgosu
 *
 */
@RestController
public class AutenticacionRestController {

	Logger logger = LoggerFactory.getLogger(AutenticacionRestController.class);
	
	//No se debe devolver un primitivo si queremos tratarlo como JSON
	@GetMapping(value= "/isLogged")
	public Map<String,Boolean> isLogged(Principal principal) {
		if (principal != null) {
			logger.info("autenticado: " + principal.getName());
		}
		return Collections.singletonMap("logged", principal != null);
	}
}
