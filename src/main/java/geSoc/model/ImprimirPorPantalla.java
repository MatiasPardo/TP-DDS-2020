package geSoc.model;

import geSoc.validadores.*;

public class ImprimirPorPantalla implements FormasDeNotificacion{

	@Override
	public void imprimir(NotificacionDeOperacionDeEgreso not) {
		System.out.println("---Operacion: "+not.getOperacionDeEgreso().getDescripcion()+"-----------------------------");
		for(ResultadoValidacion resultado: not.getResultados()){
			System.out.println(resultado.toString());
		}
		if(not.isEsValida()){
			  System.out.println("*****La operacion es VALIDA**********");
		}else System.out.println("*****La operacion es INVALIDA********");
		System.out.println("----------------------------------------");
	}

}
