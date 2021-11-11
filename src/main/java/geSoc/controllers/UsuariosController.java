package geSoc.controllers;

import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import geSoc.organizacion.Usuarie;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class UsuariosController implements WithGlobalEntityManager{

	 public ModelAndView getLoginForm(Request request, Response response) {
		 Map<String, String> modelo = new HashMap<>();
		 String estado = request.session().attribute("estado");
		 modelo.put("error", estado);
		 return new ModelAndView(modelo, "form-login.html.hbs");
	 }

	 
	 public ModelAndView iniciarSesion(Request request, Response response) {
		 Boolean esCorrecta = false;
		 System.out.println(request.queryParams("username"));
		 System.out.println(request.queryParams("password"));
		 Usuarie usuarie1 = buscarPorNombre(request.queryParams("username"));
		 List<Usuarie> usuarios = buscarUsuarios();
		 
		 if (usuarie1 != null) {
			 esCorrecta = usuarie1.getPassHasheada().equals(usuarie1.hashPass(request.queryParams("password")));	 
		 }
		 if (esCorrecta) {
			 request.session().attribute("idUsuario",usuarie1.getId().toString());
			 response.redirect("/");		 
		 }else {
	//		 return new ModelAndView(null, "form-login.html.hbs");
			 request.session().attribute("estado", "Usuario/Contraseña incorrectos");
			 response.redirect("/login");	 
		 }	 
		 return null;
	 }
	 
	 public Usuarie buscarPorNombre(String nombre) {
		 
		 try {
			 return entityManager() //
			        .createQuery("from Usuarie u where u.nombre = :nombre", Usuarie.class) //
			        .setParameter("nombre", nombre) //
			        .setMaxResults(1)
			        .getSingleResult();
			 }catch(Exception e) {
				 return null;
			 }

		  }
	 public List<Usuarie> buscarUsuarios() {
		 
		 try {
			 return entityManager() //
			        .createQuery("from Usuarie u ", Usuarie.class) //
			   //     .setParameter("nombre", nombre) //
			        .getResultList();
			 }catch(Exception e) {
				 return null;
			 }

		  }


	public static boolean esValido(String idUsuario,EntityManager entity) {
		Usuarie usuarie = new Usuarie();
		 try {
			 usuarie = entity 
			        .createQuery("from Usuarie u where u.id = "+idUsuario, Usuarie.class) 
//			        .setParameter("id", idUsuario) 
			        .getSingleResult();
			 return (usuarie != null)?true:false;
			 }catch(Exception e) {
				 return false;
			 }
	}
	
	 public ModelAndView cerrarSesion(Request request) {
		 request.session().removeAttribute("idUsuario");
		 return new ModelAndView(null, "form-login.html.hbs");
	 }

	 
	 public static Usuarie buscarPorID(String id,EntityManager entity) {
		 try {
			 return entity //
			        .createQuery("from Usuarie u where u.id = :id", Usuarie.class) //
			        .setParameter("id", Long.valueOf(id)) //
			        .setMaxResults(1)
			        .getSingleResult();
			 }catch(Exception e) {
				 return null;
			 }

		  }
}
