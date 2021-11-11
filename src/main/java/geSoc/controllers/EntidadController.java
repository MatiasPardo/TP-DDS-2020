package geSoc.controllers;

import java.util.*;
import org.uqbarproject.jpa.java8.extras.*;
import org.uqbarproject.jpa.java8.extras.transaction.*;

import geSoc.organizacion.*;
import geSoc.repositorios.RepositorioEntidades;
import spark.*;

public class EntidadController implements WithGlobalEntityManager, TransactionalOps {

	
	public ModelAndView getEntidad(Request request) {
		String entidadSeleccionada = request.queryParams("tipoEntidad");
		
    	String estado = request.session().attribute("estadoEntidad");
		String idUsuario = request.session().attribute("idUsuario");
		List<EntidadBase> entidadesBase = RepositorioEntidades.instacia.buscarEntidades(null) ;
		Map<String, Object> modelo = new HashMap<>();
		if(idUsuario!=null) {
			if(UsuariosController.esValido(idUsuario, entityManager())){
				
				if(estado!=null){
				//modelo.put("estadoEntidad",new String(estado));
				request.session().removeAttribute("estadoEntidad");
				}
				modelo.put("estadoEntidad",estado);
				modelo.put("entidades", entidadesBase);
				modelo.put("logeado", true);
				return new ModelAndView(modelo, entidadSeleccionada+".html.hbs");
			}
		}
    	
		

		return new ModelAndView(null, "form-login.html.hbs");
    }

	
	public ModelAndView crearEntidadJuridica(Request request, Response response) {
		
			EntidadJuridica entidad = this.crearEntidadJ(request);
			withTransaction(() -> {
				entityManager().persist(entidad);
			});
			request.session().attribute("estadoEntidad","Entidad Juridica creada con exito");
			response.redirect("/crearEntidad?tipoEntidad=entidadJuridica");
			return null;
			

	}

	public ModelAndView crearEntidadBase(Request request, Response response) {
	
		
		EntidadBase entidad = this.crearEntidadB(request);
		withTransaction(() -> {
			entityManager().persist(entidad);
		});
			
		request.session().attribute("estadoEntidad","Entidad Base creada con exito");
	
		response.redirect("/crearEntidad?tipoEntidad=entidadBase");
		return null;
			

	}
	
	private EntidadBase crearEntidadB(Request request) {
		String nombreFicticio = request.queryParams("nombreFicticio");
		String descripcion = request.queryParams("descripcion");
		return new EntidadBase(nombreFicticio, descripcion);
	}

	private EntidadJuridica crearEntidadJ(Request request) {
		String categoria = request.queryParams("categoria");
		String razonSocial = request.queryParams("razonsocial");
		String nombreFicticio = request.queryParams("nombreFicticio");
		String cuit = request.queryParams("cuit");
		String direccionPostal = request.queryParams("direccionPostal");
		String igj = request.queryParams("igj");

		System.out.println(request.queryParams("checkboxEntidad"));
		String[] checkboxEntidad = request.queryMap("checkboxEntidad").values();
		String idsArmadosEntidad = juntarIds(checkboxEntidad);
		List<EntidadBase> entidades = RepositorioEntidades.instacia.buscarEntidades(idsArmadosEntidad);

		TipoEntidad tipoDeEmpresa = null;
		String tipoEntidad = request.queryParams("tipo");
		if(TipoEntidad.EMPRESA.toString().equals(tipoEntidad)){
			tipoDeEmpresa = TipoEntidad.EMPRESA;
		}else{
			tipoDeEmpresa = TipoEntidad.OSC;
		}
		
		return new EntidadJuridica(RepositorioEntidades.instacia.buscarCategoria(categoria), razonSocial, nombreFicticio, cuit, direccionPostal, igj, entidades, tipoDeEmpresa);
		
	}

	public String juntarIds(String[] ids){
		StringBuilder idQuery = new StringBuilder();
		idQuery.append("(");
		for(int i =0;i<ids.length;i++){
			idQuery.append(ids[i]);
			if(i < ids.length - 1){
				idQuery.append(",");
			}
		}
		idQuery.append(")");
		return idQuery.toString();
	}
	

/*	public ModelAndView getCategorias(Request request) {
		Map<String, Object> modelo = new HashMap<>(); 
		List<Categoria> categorias = RepositorioEntidades.instacia.buscarCategorias();
		modelo.put("categorias",categorias);
		return new ModelAndView(modelo, "entidades.html.hbs");
	} */
	
	
	public ModelAndView getCategorias(Request request) {
    	String idUsuario = request.session().attribute("idUsuario");
		Map<String, Object> modelo = new HashMap<>();
    	if(UsuariosController.esValido(idUsuario, entityManager())){
    		modelo.put("logeado", true);    		
    		return new ModelAndView(modelo, "categorias.html.hbs");
    	}else{
    		return new ModelAndView(null, "form-login.html.hbs");
    	}
	}

	public ModelAndView getEntidadesPorCategoria(Request request) {
		Map<String, Object> modelo = new HashMap<>(); 
    	String idUsuario = request.session().attribute("idUsuario");
    	
    	if(!(UsuariosController.esValido(idUsuario, entityManager()))){
    		return new ModelAndView(null, "form-login.html.hbs");
    	}
		String nombreCategoria = request.queryParams("categoria");
		modelo.put("hayEntidades", true);
		List<EntidadJuridica> entidadesJuridicas = RepositorioEntidades.instacia.buscarEntidadesPorCategoria(nombreCategoria);
		if (entidadesJuridicas == null || entidadesJuridicas.isEmpty()) {
			modelo.put("hayEntidades", false);
		}
		modelo.put("categoria", nombreCategoria);
		modelo.put("entidades", entidadesJuridicas);
		modelo.put("logeado", true);  
		return new ModelAndView(modelo, "entidadesXCategoria.html.hbs");
	}
	


}
