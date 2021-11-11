package geSoc.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;

import com.sun.jersey.api.uri.UriComponent.*;

import geSoc.compra.*;
import geSoc.excepcion.*;
import geSoc.organizacion.*;
import geSoc.reporte.*;

@Entity
public class OperacionDeEgreso {
	
	public OperacionDeEgreso() {
		super();
	}
	

	@Id
	@GeneratedValue
	private Long id;
	
	public void setId(Long id) {
		this.id = id;
	}


	@Convert(converter = LocalDateConverter.class)
	private LocalDate fechaOperacion;
	
	private String descripcion;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name="operacionDeEgreso_id")
	private List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
	
	@Enumerated
	private CriterioPresupuesto criterioPresupuesto = null;
	
	@OneToMany(cascade = {CascadeType.ALL})//,fetch=FetchType.EAGER)
	@JoinColumn(name = "operacionDeEgreso_id")
	private List<Usuarie> revisoresDeCompra;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Compra compra ;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "operacionDeEgreso_id")
	private List<Etiqueta> etiquetas= new ArrayList<Etiqueta>();

	
	public void setEtiquetas(List<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public OperacionDeEgreso(LocalDate fechaOperacion, String descripcion, CriterioPresupuesto criterioPresupuesto,
			List<Usuarie> revisoresDeCompra) {
		this.fechaOperacion = fechaOperacion;
		this.descripcion = descripcion;
		this.criterioPresupuesto = criterioPresupuesto;
		this.revisoresDeCompra = revisoresDeCompra;

	}

	public void quitarRevisorDeCompra(Usuarie usuarie){
		this.revisoresDeCompra.remove(usuarie);
	}

	public CriterioPresupuesto getCriterioPresupuesto() {
		return criterioPresupuesto;
	}

	public List<Presupuesto> getPresupuestos() {
		return presupuestos;
	}

	public void setPresupuesto(List<Presupuesto> presupuestos) {
		this.presupuestos = presupuestos;
	}

	public void agregarPresupuesto(Presupuesto presupuesto) {
		presupuestos.add(presupuesto);
	}

	public List<Usuarie> getRevisoresDeCompra() {
		return revisoresDeCompra;
	}

	public void agregarRevisorDeCompra(Usuarie revisoresDeCompra) {
		this.revisoresDeCompra.add(revisoresDeCompra);
	}

	public Compra getCompra() {
		return compra;
	}

	private void setCompra(Compra compra) {
		this.compra = compra;
	}

	public void comprar(MedioDePago medioDePago) {
		List<Presupuesto> presupuestoAceptado = presupuestoAceptado();
		if (!presupuestoAceptado.isEmpty()) {		//	if (presupuestos.stream().filter(p->p.aceptado).count() == 1) {
			Compra compra = new Compra(LocalDate.now(), medioDePago, presupuestoAceptado.get(0).getPrecioTotal() );
			this.setCompra(compra);
		}else {
			throw new SinPresupuestoAceptadoException("No hay presupuesto aceptado en la Operacion de Egreso. Gracias, vuelva pronto:)");
		}
	}

	private List<Presupuesto> presupuestoAceptado() {
		return presupuestos.stream().filter(p->p.isAceptado()).collect(Collectors.toList());
	}

	public Presupuesto obtenerPresupuestoMasBajo() {
		//optional
		if(!this.getPresupuestos().isEmpty()){
			Presupuesto menor = Collections.min(this.getPresupuestos(),Comparator.comparing(c->c.getPrecioTotal()));
			return menor;
		}else return null;
	}

	public void comprarSinPresupuesto(MedioDePago medioDePago,Double precioTotalDeLaCompra) {
		Compra compra = new Compra(LocalDate.now(), medioDePago, precioTotalDeLaCompra );
		this.setCompra(compra);
	}

	public String getDescripcion(){
		return this.descripcion;
	}

	public List<Etiqueta> getEtiquetas() {
		return this.etiquetas;
	}

	public void agregarEtiqueta(Etiqueta etiqueta) {
		this.etiquetas.add(etiqueta);

	}
	
	public Double getPrecioAcepto() {

		if(!presupuestoAceptado().isEmpty()){
			return presupuestoAceptado().get(0).getPrecioTotal();
		}return 0.0;
	}

	public Proveedor getProveedor() {
		List<Presupuesto> presupuestos = this.presupuestos.stream().filter(p->p.isAceptado()).collect(Collectors.toList());
		return presupuestos.isEmpty()?null:presupuestos.get(0).getProveedor();
	}

	public Long getId() {
		return this.id;
	}
	
	


}