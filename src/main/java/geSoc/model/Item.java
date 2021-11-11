package geSoc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Item {
	
	public Item() {
		super();
	}

	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	private String descripcion;
	private int cantidad;
	private double precioUnitario;

	public Long getId() {
		return this.id;
	}
	public double getPrecioTotal() {
		return cantidad*precioUnitario;
	} // articulo o servicio

	public Item(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public Item(String nombre, String descripcion, int cantidad, double precioUnitario) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


}