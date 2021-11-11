package geSoc.organizacion;

import java.util.*;

import javax.persistence.*;

import geSoc.excepcion.*;
import geSoc.model.*;

@Entity
public class ONG extends Categoria{
	
	public ONG() {
		super();
	}

	@ManyToMany
	@JoinTable(name="EntidadesBloqueadas")
	@JoinColumn(name="ong_id")
	List<EntidadBase> entidadesBloqueadas = new LinkedList<EntidadBase>();
	
	String nombre = "ONG";
	
	@Override
	public void agregarEntidadBase(EntidadBase entidad, EntidadJuridica entidadJuridica) {
		if(!entidadesBloqueadas.contains(entidad)){
			entidadJuridica.agregarEntidadBase(entidad);
		}else throw new EntidadBloqueadaException("�ntidad base bloqueada");
	}

	public List<EntidadBase> getEntidadesBloqueadas() {
		return entidadesBloqueadas;
	}

	public void setEntidadesBloqueadas(List<EntidadBase> entidadesBloqueadas) {
		this.entidadesBloqueadas = entidadesBloqueadas;
	}

	public ONG(List<EntidadBase> entidadesBloqueadas) {
		super();
		this.entidadesBloqueadas = entidadesBloqueadas;
	}

	public String getNombre() {
		return this.nombre;
	}

}
