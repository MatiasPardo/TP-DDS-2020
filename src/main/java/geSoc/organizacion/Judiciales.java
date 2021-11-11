package geSoc.organizacion;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import geSoc.excepcion.*;
import geSoc.model.*;

@Entity
public class Judiciales extends Categoria{
	
	Boolean puedeAgregar;
	String nombre = "Judiciales";
	
	public Judiciales() {
		super();
	}

	public Judiciales(Boolean puedeAgregar) {
		super();
		this.puedeAgregar = puedeAgregar;
	}

	@Override
	public void agregarEntidadBase(EntidadBase entidad, EntidadJuridica entidadJuridica) {
		if(puedeAgregar){
			entidadJuridica.agregarEntidadBase(entidad);
		}else throw new TieneBloqueadoAgregarEntidadBase("No esta permitido agregar una entidad base en la Entidad Juridica");
		
	}

	public Boolean getPuedeAgregar() {
		return puedeAgregar;
	}

	public void setPuedeAgregar(Boolean puedeAgregar) {
		this.puedeAgregar = puedeAgregar;
	}

	public String getNombre() {
		return this.nombre;
	}

}
