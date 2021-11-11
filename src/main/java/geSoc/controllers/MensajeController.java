package geSoc.controllers;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.*;

import geSoc.model.CriterioPresupuesto;
import geSoc.model.NotificacionDeOperacionDeEgreso;
import geSoc.model.OperacionDeEgreso;
import geSoc.organizacion.*;
import geSoc.validadores.ResultadoValidacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
public class MensajeController implements WithGlobalEntityManager{

    public ModelAndView getMensajes(Request request,Response response) {
        Map<String, Object> modelo = new HashMap<>();  
		String idUsuario = request.session().attribute("idUsuario");
		if(idUsuario != null && UsuariosController.esValido(idUsuario, entityManager())){
			Usuarie usuarie = UsuariosController.buscarPorID(idUsuario, entityManager());
		    modelo.put("logeado", true);
			List<NotificacionDeOperacionDeEgreso> notis = this.todasLasNotificaciones();
			System.out.println(notis);
		    modelo.put("mensajes",notis);
		    return new ModelAndView(modelo, "mensajes.html.hbs");
		}else{
			return new ModelAndView(null, "form-login.html.hbs");
		}

    }
    
    public List<NotificacionDeOperacionDeEgreso> todasLasNotificaciones(){
    	List<NotificacionDeOperacionDeEgreso> notis = null;
		try{
			notis = entityManager()
					.createQuery("from NotificacionDeOperacionDeEgreso",NotificacionDeOperacionDeEgreso.class)
					.getResultList();
			return notis;
		}catch (Exception e){
			return notis;
		}
    }
}
