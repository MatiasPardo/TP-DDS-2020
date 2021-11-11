package geSoc.reporte;

import java.util.*;
import java.util.stream.*;

import geSoc.model.*;
import geSoc.organizacion.*;

public class Reporte {
	
	private Map<TipoEtiqueta,List<OperacionDeEgreso>> agrupacionMarilyn = new HashMap<TipoEtiqueta,List<OperacionDeEgreso>>();
	private Map<Proveedor,List<OperacionDeEgreso>> grupoSombras = new HashMap<Proveedor,List<OperacionDeEgreso>>();
	private EntidadJuridica entidad;
	
	public Reporte(EntidadJuridica entidad){
		this.entidad = entidad;
	}
/*	public Reporte(Map<TipoEtiqueta,List<OperacionDeEgreso>> operacionesAgrupoadasPorTipo,
			Map<Proveedor,List<OperacionDeEgreso>> operacionesAgrupadasPorProveedor){
		this.agrupacionMarilyn = operacionesAgrupoadasPorTipo;
		this.grupoSombras = operacionesAgrupadasPorProveedor;
	}
*/
	public Reporte generarReporte(){
		List<Proveedor> proveedores = new LinkedList<Proveedor>(entidad.proveedores());
		for(TipoEtiqueta tipo: TipoEtiqueta.etiquetas()){
			if(!tipo.equals(TipoEtiqueta.PorProveedor)){
				this.agrupacionMarilyn.put(tipo, this.entidad.filtrarPorTipo(tipo));
			}else if(tipo.equals(TipoEtiqueta.PorProveedor)){
				for(Proveedor proveedor: proveedores){
					this.grupoSombras.put(proveedor,this.entidad.filtrarPorProveedor(proveedor));
				}
			}
		}
		return this;
		
		
	}
	
	
}
