package geSoc.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import geSoc.validadores.*;
@Entity
public class NotificacionDeOperacionDeEgreso {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = {CascadeType.ALL})
	private OperacionDeEgreso operacionDeEgreso;
	
	@ElementCollection(targetClass = ResultadoValidacion.class)
	@Enumerated(EnumType.STRING)
	private List<ResultadoValidacion> resultados = new LinkedList<ResultadoValidacion>();
	
	private boolean esValida;

	public NotificacionDeOperacionDeEgreso() {
		
	}
	

	public NotificacionDeOperacionDeEgreso(OperacionDeEgreso operacionDeEgreso,boolean esValida, List<ResultadoValidacion> resultados) {
		super();
		this.setResultados(resultados);
		this.setEsValida(esValida);
		this.operacionDeEgreso = operacionDeEgreso;
	}

	public void NotificarResultado(FormasDeNotificacion formasDeNotificacion){
		formasDeNotificacion.imprimir(this);
	}

	public OperacionDeEgreso getOperacionDeEgreso() {
		return operacionDeEgreso;
	}

	public void setEsValida(boolean esValida) {
		this.esValida = esValida;
	}

	public List<ResultadoValidacion> getResultados() {
		return resultados;
	}

	public void setResultados(List<ResultadoValidacion> resultados) {
		this.resultados = resultados;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isEsValida() {
		return esValida;
	}

}
