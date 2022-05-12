package cl.aiep.java.cft;

import javax.validation.Valid; //dependencia para validar el formulario

import org.springframework.stereotype.Controller; // para convertir la clase en controlador
import org.springframework.validation.BindingResult;// para validar
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //pasar clase a controlador

public class cftController {

	//se creara dos metodos, un index y un formulario
	
	
	//pagina principal
	@GetMapping("/") //ruta del metodo
	public String index() {
		return "index"; //nombre de la plantilla de thymeleaf
	}
	
	//formulario
	
	@GetMapping("/alumno") //ruta del metodo
	public String alumnoForm(Alumno alumno) {
		return "alumno-form";
		
	}
	
	//metodo formulario con Posmapping y VALIDACION
	
	@PostMapping("/alumno")
	public String alumnoProcesar(@Valid Alumno alumno, BindingResult informeValidacion) { //despues de la dependencia debe ir la clase a validar y luego el bindingResult
		if(informeValidacion.hasErrors()) {
			return "alumno-form"; // Si hubo errpores, vuelvo a la vista del form
		}
		return "alumno-ficha"; // si NO hubieron errores, muestro la ficha del alumno
	}
	
}
