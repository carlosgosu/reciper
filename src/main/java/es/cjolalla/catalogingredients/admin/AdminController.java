package es.cjolalla.catalogingredients.admin;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@GetMapping(value= {"/"})
	@PreAuthorize("hasRole('ADMIN')")
	public String autenticacion(HttpSession session) {
		return "admin/admin";
	}
}
