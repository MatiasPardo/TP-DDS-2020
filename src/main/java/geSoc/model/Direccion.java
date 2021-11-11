package geSoc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.gson.*;

import geSoc.apis.*;

@Entity
public class Direccion {
	
	public Direccion() {
		super();
	}

	@Id
	@GeneratedValue
	private Long id;
	
	private String calle;
	
	private String altura;
	
	private String piso;
	
	private String ciudad;
	
	private String provincia;
	
	private String pais;
	
	private String codigoPostal;
	
	public Direccion(String calle, String altura, String piso, String codigoPostal){
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.setCodigoPostal(codigoPostal);
		
	}
	
	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	public void cargarDatosConApi(UbicacionYMoneda ubicacion){
		ubicacion.cargarDatosCP(this);		
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	

}
