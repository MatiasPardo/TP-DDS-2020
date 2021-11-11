package geSoc.calendarizacion;

import java.util.*;

import javax.persistence.*;

@Entity
public class Periodo {

	public Periodo() {
		super();
	}

	public Periodo(Date inicio, Date fin, int cantidadRepeticiones, int intervaloRepeticion) {
		this.fechaInicio = inicio;
		this.fechaFin = fin;
		this.cantidadRepeticiones = cantidadRepeticiones;
		this.intervaloRepeticion = intervaloRepeticion;
	}

	@Id
	@GeneratedValue
	private Long id;
	
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	private int cantidadRepeticiones;
	
	private int intervaloRepeticion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getCantidadRepeticiones() {
		return cantidadRepeticiones;
	}

	public void setCantidadRepeticiones(int cantidadRepeticiones) {
		this.cantidadRepeticiones = cantidadRepeticiones;
	}

	public int getIntervaloRepeticion() {
		return intervaloRepeticion;
	}

	public void setIntervaloRepeticion(int intervaloRepeticion) {
		this.intervaloRepeticion = intervaloRepeticion;
	}
	
	
}
