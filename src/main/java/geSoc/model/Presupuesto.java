package geSoc.model;

import java.util.List;

import javax.persistence.*;

import geSoc.organizacion.*;

@Entity
public class Presupuesto {
	
	public Presupuesto() {
		super();
	}

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	private Documento documento;
	
	@ManyToOne
	private Proveedor proveedor;
	
	@OneToMany
	@JoinColumn(name="presupuesto_id")
	private List<Item> presupuestoPorItem;
	
	private boolean aceptado;

	public double getPrecioTotal() {
		double precioTotal = 0;
		for (int i=0; i<presupuestoPorItem.size() ;i++) {
			precioTotal += presupuestoPorItem.get(i).getPrecioTotal();
		}
		return precioTotal;
	}

	public boolean isAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

	public Presupuesto(Documento documento, Proveedor proveedor, List<Item> presupuestoPorItem) {
		super();
		this.documento = documento;
		this.setProveedor(proveedor);
		this.presupuestoPorItem = presupuestoPorItem;
		this.aceptado = false;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}



}
