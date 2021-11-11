package geSoc.model;

import java.util.*;

import geSoc.validadores.*;

public enum CriterioPresupuesto {
	MENORVALOR{
		@Override
		public boolean esValido(OperacionDeEgreso operacion, List<ResultadoValidacion> resultados){
			
			if(operacion.getPresupuestos().isEmpty()){
				resultados.add(ResultadoValidacion.NoCuentaConPresupuestos);
				return false;
			}else if(operacion.obtenerPresupuestoMasBajo().equals(operacion.getPresupuestos().stream().filter(p->p.isAceptado()).findFirst().get())){
				resultados.add(ResultadoValidacion.CriterioSeleccionValido);
				return true;
			}else {
				resultados.add(ResultadoValidacion.CriterioSeleccionInvalido);
				return false;
			}
		}
	}, NINGUNO{
		
		@Override
		public boolean esValido(OperacionDeEgreso operacion,List<ResultadoValidacion> resultados) {
			return true;
		}
	};

	public boolean esValido(OperacionDeEgreso operacion,List<ResultadoValidacion> resultados) {
		// TODO Auto-generated method stub
		return false;
	}

}
