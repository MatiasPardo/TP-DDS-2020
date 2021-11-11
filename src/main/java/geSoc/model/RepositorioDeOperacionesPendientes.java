package geSoc.model;

import java.util.*;
import javax.persistence.*;

import org.uqbarproject.jpa.java8.extras.*;
import org.uqbarproject.jpa.java8.extras.transaction.*;

import geSoc.organizacion.*;
import geSoc.validadores.*;



public class RepositorioDeOperacionesPendientes implements WithGlobalEntityManager{

	
	public List<OperacionDeEgreso> operacionesPendientes = new LinkedList<OperacionDeEgreso>();
	
	public List<ValidadorDeOperacion> bateriaValidadores = new LinkedList<ValidadorDeOperacion>();;
		
	public List<NotificacionDeOperacionDeEgreso> validarOperaciones() {
		List<NotificacionDeOperacionDeEgreso> notis = new LinkedList<NotificacionDeOperacionDeEgreso>();
		for(OperacionDeEgreso operacion: operacionesPendientes){
			//operacion = entityManager().merge(operacion);
			boolean esValida = true;
			List<ResultadoValidacion> resultados = new LinkedList<ResultadoValidacion>();
			for(ValidadorDeOperacion validador: bateriaValidadores){
				if(!validador.esOperacionValida(operacion,resultados)){
					esValida = false;
				}
			}
			notis.addAll(notificarRevisoresDeCompra(esValida,operacion, resultados));
		}
		return notis;
	}
	/*
	public construirValidadores(){
		new Valida
	}*/
	
	public  List<NotificacionDeOperacionDeEgreso> notificarRevisoresDeCompra(boolean esValida, OperacionDeEgreso operacion, List<ResultadoValidacion> resultados) {
		List<NotificacionDeOperacionDeEgreso> notis = new LinkedList<NotificacionDeOperacionDeEgreso>();
		for(Usuarie user: operacion.getRevisoresDeCompra()){
			NotificacionDeOperacionDeEgreso noti = new NotificacionDeOperacionDeEgreso(operacion, esValida, resultados);
			user.agregarNotificacion(noti);
			notis.add(noti);
		}
		return notis;
	}

	public List<OperacionDeEgreso> getOperacionesPendientes() {
		return operacionesPendientes;
	}

	public void setOperacionesPendientes(List<OperacionDeEgreso> operacionesPendientes) {
		this.operacionesPendientes = operacionesPendientes;
	}

	public List<ValidadorDeOperacion> getBateriaValidadores() {
		return bateriaValidadores;
	}

	public void setBateriaValidadores(List<ValidadorDeOperacion> bateriaValidadores) {
		this.bateriaValidadores = bateriaValidadores;
	}

	public void agregarOperacionDeEgreso(OperacionDeEgreso operacionDeEgreso) {
		this.operacionesPendientes.add(operacionDeEgreso);
	}

	public void quitarOperacionEgreso(OperacionDeEgreso operacionDeEgreso) {
		this.operacionesPendientes.remove(operacionDeEgreso);
	}

	public void removerValidadores(ValidadorDeOperacion validador) {
		bateriaValidadores.remove(validador);
	}

	public void agregarValidadores(ValidadorDeOperacion validador){
		bateriaValidadores.add(validador);
	}


}
