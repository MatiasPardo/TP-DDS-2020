package geSoc.reporte;

import javax.persistence.*;


@Entity
public class PorTipo extends Etiqueta {
	
	public PorTipo(){
		
	}

	public PorTipo(TipoEtiqueta tipo) {
		this.setTipo(tipo);
	}


}
