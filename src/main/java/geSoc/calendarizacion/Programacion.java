package geSoc.calendarizacion;

import java.util.*;

import javax.persistence.*;

import org.quartz.*;

import geSoc.model.*;

@Entity
public class Programacion {
	
	public Programacion() {
		super();
	}
	
	public Programacion(String identificador, List<OperacionDeEgreso> operacionesPendientes, Periodo periodo) {
		super();
		this.estaActiva = true;
		this.operacionesPendientes = operacionesPendientes;
		this.periodo = periodo;
		this.identificador = identificador;
	}

	@Id
	@GeneratedValue
	private Long id;
	
	private String identificador;
	
	private boolean estaActiva;
	
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name="programacion_id")
	private List<OperacionDeEgreso> operacionesPendientes = new LinkedList<OperacionDeEgreso>();
		
	
	@ManyToOne
	private Periodo periodo;

	public List<OperacionDeEgreso> getOperacionesPendientes() {
		return operacionesPendientes;
	}

	public void setOperacionesPendientes(List<OperacionDeEgreso> operacionesPendientes) {
		this.operacionesPendientes = operacionesPendientes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isEstaActiva() {
		return estaActiva;
	}

	public void setEstaActiva(boolean estaActiva) {
		this.estaActiva = estaActiva;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}	
	

}
