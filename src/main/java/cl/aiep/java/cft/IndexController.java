package cl.aiep.java.cft;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

		//pagina principal
		@GetMapping("/")
		public String index() {
			return "index";
		}
}
