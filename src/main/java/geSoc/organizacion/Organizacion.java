package geSoc.organizacion;

import java.util.List;

import geSoc.excepcion.OrganizacionSinEntidadesJuridicas;

public class Organizacion {

	private String nombre;
	private List<EntidadJuridica> entidadesJuridicas ;

	public Organizacion(String nombre, List<EntidadJuridica> entidadesJuridicas) {
		super();
		this.nombre = nombre;
		if (entidadesJuridicas == null) {
			throw new OrganizacionSinEntidadesJuridicas("no hay entdades juridicas asignadas a la organizacion");
		}
		this.entidadesJuridicas = entidadesJuridicas;
	}




}
