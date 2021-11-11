package geSoc.organizacion;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import geSoc.excepcion.*;
import geSoc.model.*;

@Entity
public class IndustriaAgropecuaria extends Categoria{

	public IndustriaAgropecuaria(){
		super();
	}
	
	Double montoMaximo = 0.0;
	String nombre = "Industria Agropecuaria";
	
	@Override
	public void agregarEntidadBase(EntidadBase entidad, EntidadJuridica entidadJuridica) {
		
		
	}

	public IndustriaAgropecuaria(Double montoMaximo) {
		this.montoMaximo = montoMaximo;
	}
	
	@Override
	public void agregarOperacionDeEgreso(OperacionDeEgreso operacion, EntidadJuridica entidadJuridica){

		if(operacion.getPrecioAcepto() < montoMaximo){
			entidadJuridica.agregarOperacion(operacion);
		}else throw new NoCumpleMontoMaximo("El precio supera al monto maximo establecido");
	}

	public String getNombre() {
		return this.nombre;
	}

}
