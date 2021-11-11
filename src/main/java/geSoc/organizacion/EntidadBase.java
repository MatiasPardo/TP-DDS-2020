package geSoc.organizacion;

import javax.persistence.*;

@Entity
public class EntidadBase {
	
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	private String nombreFicticio;
	private String descripcion;
	
	public EntidadBase() {
		
	}

	public EntidadBase(String nombreFicticio, String descripcion) {
		super();
		this.setNombreFicticio(nombreFicticio);
		this.descripcion = descripcion;
	}


	public String getNombreFicticio() {
		return nombreFicticio;
	}


	public void setNombreFicticio(String nombreFicticio) {
		this.nombreFicticio = nombreFicticio;
	}
}