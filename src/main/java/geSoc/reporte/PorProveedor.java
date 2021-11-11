package geSoc.reporte;

import javax.persistence.*;

import geSoc.model.*;

@Entity
public class PorProveedor extends Etiqueta {

	public PorProveedor(){
	}
	
	@ManyToOne
	private Proveedor proveedor;

	public PorProveedor(Proveedor proveedor) {
		this.setTipo(TipoEtiqueta.PorProveedor);
		this.proveedor = proveedor;
	}

	@Override
	public boolean esProveedor(Proveedor proveedor_){
		return proveedor.equals(proveedor_);
	}



}
