package geSoc.controllers;

import java.time.*;
import java.util.*;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.*;

import geSoc.model.*;
import geSoc.organizacion.Usuarie;
import spark.*;


public class OpEgresoController implements WithGlobalEntityManager, TransactionalOps {

    public ModelAndView getOpEgreso(Request request) {
    	String estado = request.session().attribute("estado");
    	String idUsuario = request.session().attribute("idUsuario");
		Map<String, Object> modelo = new HashMap<>();   
		List<Usuarie> listaUsuarios = buscarUsuarios();
    	modelo.put("usuarios", listaUsuarios);

    	if(UsuariosController.esValido(idUsuario, entityManager())){
	
	    	if(estado != null){
				
	        	modelo.put("estadoOperacion", estado);
	        	modelo.put("visible", true);
	        	modelo.put("logeado", true);
	        	return new ModelAndView(modelo, "opEgreso.html.hbs");
	    	}else{
				
	        	modelo.put("visible", true);
	        	modelo.put("logeado", true);
	        	return new ModelAndView(modelo, "opEgreso.html.hbs");
	    	}
    	}else{
        	return new ModelAndView(null, "form-login.html.hbs");
    	}
    	
    
    }
    
    public List<Usuarie> buscarUsuarios() {
		 
		 try {
			 return entityManager() //
			        .createQuery("from Usuarie u ", Usuarie.class) //
			   //   .setParameter("nombre", nombre) //
			        .getResultList();
			 }catch(Exception e) {
				 return null;
			 }

	}

	public Void crearOpEgreso(Request request, Response response) {
		String descripcion = request.queryParams("descripcion");
		String criterio = request.queryParams("criterio");
		CriterioPresupuesto criterioSeleccionado = CriterioPresupuesto.valueOf(criterio);
		String[] idsUsuario = request.queryMap("checkboxes").values();
		StringBuilder idQuery = new StringBuilder();
		idQuery.append("(");
		for(int i =0;i<idsUsuario.length;i++){
			idQuery.append(idsUsuario[i]);
			if(i < idsUsuario.length - 1){
				idQuery.append(",");
			}
		}
		idQuery.append(")");
		List<Usuarie> usuaries = buscarUsuarios(idQuery.toString());
		OperacionDeEgreso nuevaOp = new OperacionDeEgreso(LocalDate.now(),descripcion,criterioSeleccionado,usuaries);
		withTransaction(() -> {
			entityManager().persist(nuevaOp);
		});
		Map<String, String> modelo = new HashMap<>();
		//modelo.put("estadoOperacion","Operacion de egreso creado con exito");
		request.session().attribute("estado","Operacion de egreso creada con exito");
		response.redirect("/opEgreso");
		return null;
	}


	public List<Usuarie> buscarUsuarios(String ids) {
		 
		 try {
			 return entityManager() //
			        .createQuery("from Usuarie u where id in "+ids, Usuarie.class) //
			       // .setParameter("ids", ids) //
			        .getResultList();
			 }catch(Exception e) {
				 return null;
			 }
	
	}
}
/*
public ModelAndView inicio(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"login.hbs");
    }
*/