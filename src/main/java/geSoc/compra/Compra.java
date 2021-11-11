package geSoc.compra;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
public class Compra {

	@Id
	@GeneratedValue
	private Long id;
	
	private LocalDate fechaOperacion;
	
	@ManyToOne (cascade = {CascadeType.ALL})
	private MedioDePago medioDePago;
	
	private double importe;

	public Compra(LocalDate fechaOperacion, MedioDePago medioDePago, double importe) {
		super();
		this.fechaOperacion = fechaOperacion;
		this.medioDePago = medioDePago;
		this.importe = importe;
	}

	public Compra(){
		
	}
	
	public double getImporte() {
		return importe;
	}




}
