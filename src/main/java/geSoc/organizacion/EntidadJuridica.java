package geSoc.organizacion;

import java.util.*;
import java.util.stream.Collectors;
import javax.persistence.*;
import geSoc.excepcion.EntidadInvalidaException;
import geSoc.model.*;
import geSoc.reporte.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

@Entity
public class EntidadJuridica{
	
	public EntidadJuridica(){
		
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String razonSocial;
	private String nombreFicticio;
	private String CUIT;
	private String direccionPostal;
	private String defCodeInscIGJ = null;
	
	@OneToMany
	@JoinColumn(name="entidadJuridica_id")
	private List<EntidadBase> entidadesBase = new LinkedList<EntidadBase>(); 
	
	@Enumerated
	private TipoEntidad tipo;
	//permitir que tenga diferentes categorias
	
	@ManyToOne
	private Categoria categoria;
	
	@OneToMany
	@JoinColumn(name = "entidadJuridica_id")
	private List<OperacionDeEgreso> operacionesDeEgreso;
	
	public EntidadJuridica(Categoria categoria, String razonS, String nombreF, String CUIT, String dirPos,
				String defCodeInscIGJ, List<EntidadBase> entidadesBase, TipoEntidad tipo) {
		if(!(entidadesBase == null || entidadesBase.size() >= 2)){
			throw new EntidadInvalidaException("La lista de entidades base tiene solo 1, debe tener 0, 2 o mas.");
		}
		this.categoria = categoria;
		this.razonSocial = razonS;
		this.nombreFicticio = nombreF;
		this.CUIT = CUIT;
		this.direccionPostal = dirPos;
		this.defCodeInscIGJ = defCodeInscIGJ;
		this.agregarEntidadBase(entidadesBase);
		this.entidadesBase = entidadesBase;
		this.operacionesDeEgreso = new ArrayList<OperacionDeEgreso>();
		this.tipo = tipo;
	}

	public List<OperacionDeEgreso> getOperacionesDeEgreso() {
		return operacionesDeEgreso;
	}

	public void setOperacionesDeEgreso(List<OperacionDeEgreso> operacionesDeEgreso) {
		this.operacionesDeEgreso = operacionesDeEgreso;
	}
	
	public void agregarEntidadBase(List<EntidadBase> entidadesBase2) {
		for(EntidadBase entidad: entidadesBase2){
			this.categoria.agregarEntidadBase(entidad, this);
		}
	}

	public TipoEntidad getTipo() {
		return this.tipo;
	}

	public Clasificacion getClasificacion(){
		return this.tipo.getClasificacion(this);
	}
	
	public List<OperacionDeEgreso> filtrarPorTipo(TipoEtiqueta tipo){
		List<OperacionDeEgreso> operacionFiltradas = new LinkedList<OperacionDeEgreso>();
		for(OperacionDeEgreso op: this.operacionesDeEgreso){
			long opFiltradas = op.getEtiquetas().stream().filter(e->e.getTipo().equals(tipo)).count();
			if(opFiltradas >0){
				operacionFiltradas.add(op);
			}
		}
		return operacionFiltradas;
	}

	public List<OperacionDeEgreso> filtrarPorProveedor(Proveedor proveedor){
		List<OperacionDeEgreso> operacionFiltradas = new LinkedList<OperacionDeEgreso>();
		for(OperacionDeEgreso op: this.operacionesDeEgreso){
			if(!op.getEtiquetas().stream().filter(e->e.getTipo().equals(TipoEtiqueta.PorProveedor) && e.esProveedor(proveedor) ).collect(Collectors.toList()).isEmpty() ){
				operacionFiltradas.add(op);
			}
		}
		return operacionFiltradas;
	}
	/*
	public List<OperacionDeEgreso> filtrar(Object tipoOProveedor){
		List<OperacionDeEgreso> operacionFiltradas = new LinkedList<OperacionDeEgreso>();
		for(OperacionDeEgreso op: this.operacionesDeEgreso){
			if(tipoOProveedor instanceof Proveedor){
				List<Etiqueta> etiquetas = op.getEtiquetas().stream().filter(e-> e.getTipo().equals(TipoEtiqueta.PorProveedor) && e.esProveedor((Proveedor) tipoOProveedor)).collect(Collectors.toList());
				if()	
					
			}else
		}
	}*/
/*Hay que ver si se queda las de add operacion*/
	public void agregarOperacionDeEgreso(OperacionDeEgreso operacionDeEgreso1) {
		this.operacionesDeEgreso.add(operacionDeEgreso1);
	}

	public void agregarOperacionEgreso(OperacionDeEgreso operacion){
		this.categoria.agregarOperacionDeEgreso(operacion, this);
	}
	
	public void agregarOperacion(OperacionDeEgreso operacion){
		this.operacionesDeEgreso.add(operacion);
	}

	public void agregarEntidadBase(EntidadBase entidad) {
		this.entidadesBase.add(entidad);
	}

	public Set<Proveedor> proveedores() {
		Set<Proveedor> proveedores = new HashSet<Proveedor>();
		for(OperacionDeEgreso op: this.operacionesDeEgreso){
			if(op.getProveedor()!= null){
				proveedores.add(op.getProveedor());
			}
		}
		return proveedores;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public String getNombreFicticio() {
		return nombreFicticio;
	}

	public String getCUIT() {
		return CUIT;
	}

	public String getDireccionPostal() {
		return direccionPostal;
	}

	public String getDefCodeInscIGJ() {
		return defCodeInscIGJ;
	}

	public List<EntidadBase> getEntidadesBase() {
		return entidadesBase;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	
}

