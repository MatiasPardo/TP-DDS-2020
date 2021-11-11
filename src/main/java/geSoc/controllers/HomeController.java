package geSoc.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.*;

import geSoc.organizacion.*;
import spark.*;

public class HomeController implements WithGlobalEntityManager{

    public ModelAndView getHome(Request request) {
    	String idUsuario = request.session().attribute("idUsuario");
		Map<String, Object> modelo = new HashMap<>();
		modelo.put("logeado", false);
    	if(idUsuario != null && UsuariosController.esValido(idUsuario,entityManager())){
            modelo.put("anio", LocalDate.now().getYear());
            modelo.put("logeado", true);
    	}
    	return new ModelAndView(modelo, "index.html.hbs");

    }
    

}
