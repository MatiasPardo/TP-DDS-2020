package geSoc.organizacion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import geSoc.model.*;
import geSoc.organizacion.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Categoria {
	
	public Categoria() {
		super();
	}

	@Id
  	@GeneratedValue
  	private Long id;
 
	public abstract void agregarEntidadBase(EntidadBase entidad,EntidadJuridica entidadJuridica);

	public void agregarOperacionDeEgreso(OperacionDeEgreso operacion, EntidadJuridica entidadJuridica){
		entidadJuridica.agregarOperacion(operacion);
	}

	public Object getId() {
		return this.id;
	}


}