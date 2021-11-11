package geSoc.validadores;

import java.util.*;
import javax.persistence.*;
import geSoc.model.*;

public class ValidadorSegunCriterioSeleccion extends ValidadorDeOperacion {

	@Override
	public boolean esOperacionValida(OperacionDeEgreso operacion, List<ResultadoValidacion> resultados) {
				
		if(operacion.getCriterioPresupuesto() == null){
			resultados.add(ResultadoValidacion.CriterioSeleccionNoIndicado);
			return false;
		}else{
			return operacion.getCriterioPresupuesto().esValido(operacion,resultados);
		}
	}
}
