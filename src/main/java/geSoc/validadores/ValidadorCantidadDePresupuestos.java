package geSoc.validadores;

import java.util.*;

import javax.persistence.*;
import geSoc.model.*;


public class ValidadorCantidadDePresupuestos extends ValidadorDeOperacion{
	
	private int cantidadDePresupuestosNecesarios = 3;

	@Override
	public boolean esOperacionValida(OperacionDeEgreso operacion, List<ResultadoValidacion> resultados) {
		
		if(operacion.getPresupuestos() == null){
			resultados.add(ResultadoValidacion.NoCuentaConPresupuestos);
			return false;
		}
		if(operacion.getPresupuestos().size() >= this.cantidadDePresupuestosNecesarios){
			resultados.add(ResultadoValidacion.CantidadPresupuestoValido);
			return true;
		}else 
		{
			resultados.add(ResultadoValidacion.CantidadPresupuestoInvalido);
			return false;
		}
	}
	
	public void setCantidadDePresupuestosNecesarios(int cantidadPresupuestos){
		this.cantidadDePresupuestosNecesarios = cantidadPresupuestos;
	}

}
