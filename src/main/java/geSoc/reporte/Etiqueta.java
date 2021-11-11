package geSoc.reporte;

import javax.persistence.*;

import geSoc.model.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Etiqueta {

	public Etiqueta() {
		super();
	}

	@Id
	@GeneratedValue
	private Long id;
	 
	@Enumerated
	private TipoEtiqueta tipo;

	public TipoEtiqueta getTipo() {
		return tipo;
	}

	public void setTipo(TipoEtiqueta tipo) {
		this.tipo = tipo;
	}

	public boolean esProveedor(Proveedor proveedor) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
