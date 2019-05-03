package es.cjolalla.catalogingredients.usuarios.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	//Tambien se puede utilizar RequestMapping
	@GetMapping("/")
	public String index() {
		return "index";
	}
}
