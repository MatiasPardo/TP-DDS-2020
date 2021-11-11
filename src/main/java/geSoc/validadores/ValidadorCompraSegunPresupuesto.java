package geSoc.validadores;

import java.util.*;
import java.util.stream.*;

import javax.persistence.*;

import geSoc.model.*;

public class ValidadorCompraSegunPresupuesto extends ValidadorDeOperacion{

	@Override
	public boolean esOperacionValida(OperacionDeEgreso operacion, List<ResultadoValidacion> resultados) {
		if(operacion.getCompra() == null){
			//setear la notificacion con un resultado
			resultados.add(ResultadoValidacion.NoHayCompra);
			return true;
		}else{
			List<Presupuesto> presupuestos = operacion.getPresupuestos().stream().
					filter(p->p.getPrecioTotal() == operacion.getCompra().getImporte()).collect(Collectors.toList());
			if(!presupuestos.isEmpty()){
				resultados.add(ResultadoValidacion.CompradoConPresupuestoPropio);
				return true;
			}else {
				resultados.add(ResultadoValidacion.CompradoConUnPresupuestoNoPropio);
				return false;
			}
		}
	}
}
