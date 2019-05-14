package es.cjolalla.catalogingredients.ingrediente.web;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cjolalla.catalogingredients.ingrediente.Ingrediente;
import es.cjolalla.catalogingredients.ingrediente.IngredienteService;
import es.cjolalla.catalogingredients.ingrediente.dto.IngredienteDTO;


//Anotacion que equivale a @Controller + @ResponseBody
@RestController
@RequestMapping("/api")
public class IngredienteRestController {
	
	Logger logger = LoggerFactory.getLogger(IngredienteRestController.class);
	
	private final IngredienteService ingredienteService;
	private final ModelMapper mapper;
	
	@Autowired
	public IngredienteRestController(IngredienteService ingredienteService, ModelMapper mapper) {
		this.ingredienteService = ingredienteService;
		this.mapper = mapper;
	}
	
	@GetMapping(value= "/ingredientes")
	@PreAuthorize("hasRole('ADMIN') or hasAuthority('READ_INGREDIENTES')")
	public List<IngredienteDTO> getAllIngredientes(Authentication authentication) {
		//Para imprimir todos los permisos que tiene (y el perfil que tambien es una granted authority)
		//logger.info(authentication.getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.joining(", ","{","}")));
		List<Ingrediente> ingredientes = ingredienteService.devolverIngredientesTodos();
		
		//Para mapear una lista tenemos que indicar el tipo al que queremos que nos mappee ya que el compilador no sabe a que tipo va a querer mapear (del contenido de la lista)
		Type tipoLista = new TypeToken<List<IngredienteDTO>>() {}.getType();
		List<IngredienteDTO> dtos = mapper.map(ingredientes, tipoLista);
		return dtos;
		
	}

	@GetMapping(value= "/ingredientes/{nombre}")
	public IngredienteDTO ingredientesByNombre(@PathVariable("nombre") String nombre) {
		//Trsnformamos de la entidad al DTO (transformacion por defecto si las propiedades se llaman igual)
		Ingrediente recuperado = ingredienteService.devolverIngrediente(nombre);
		if (recuperado != null) {
			//Hay que asegurarse de que el source no es null o falla el mapper
			return mapper.map(recuperado, IngredienteDTO.class);
		} else {
			return null;
		}
	}
	
	@PostMapping(value="/ingredientes")
	public Long addIngrediente(@RequestBody IngredienteDTO ingrediente) {
		Ingrediente incluir = mapper.map(ingrediente, Ingrediente.class);
		return ingredienteService.incluirIngrediente(incluir);
	}
	
	@PutMapping("/ingredientes/{id}")
	public Ingrediente updateIngrediente(@RequestBody IngredienteDTO ingrediente, @PathVariable("id") Long id) {
		ingrediente.setId(id);
		Ingrediente incluir = mapper.map(ingrediente, Ingrediente.class);
		return ingredienteService.actualizarIngrediente(incluir);
	}
}
