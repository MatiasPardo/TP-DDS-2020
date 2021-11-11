package geSoc.reporte;

import java.util.*;

public enum TipoEtiqueta {

	Amoblamientos, Indumentaria, PorProveedor;

	public static List<TipoEtiqueta> etiquetas(){
		List<TipoEtiqueta> etiquetas = new LinkedList<TipoEtiqueta>();
		etiquetas.add(TipoEtiqueta.Amoblamientos);
		etiquetas.add(TipoEtiqueta.Indumentaria);
		etiquetas.add(TipoEtiqueta.PorProveedor);
		return etiquetas;
	}

}
 