package geSoc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Proveedor {
	
	public Proveedor() {
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	private String apellido;
	private String razonSocial;
	private String dni;
	
	@ManyToOne 
	private Direccion direccionPostal;
	
	public String getNombre() {
		return nombre;
	}
	public Long getId() {
		return id;
	}
	//TODO agregar setter y getter
	
	


}