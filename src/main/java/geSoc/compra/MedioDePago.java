package geSoc.compra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import geSoc.excepcion.*;

@Entity
public class MedioDePago {

	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated
	TipoMedioPago tipoMedioPago;
	
	@Enumerated
	Administradora administradora; //o podria llamarse empresaMedioPago

	String IdentificadorPago;

	public MedioDePago(TipoMedioPago tipoMedioPago, Administradora administradora, String identificadorPago) {
		super();
		this.tipoMedioPago = tipoMedioPago;

		if(getMedioDePagoValidos().get(tipoMedioPago).contains(administradora)) {
			this.tipoMedioPago = tipoMedioPago;
			this.administradora = administradora;
		}else throw new AdministradoraIncorrectaException("LTA");

		this.IdentificadorPago = identificadorPago;
	}
	
	public MedioDePago(){
		
	}

	public static Map<TipoMedioPago, List<Administradora> > getMedioDePagoValidos() {

		Map<TipoMedioPago, List<Administradora> > mapa = new HashMap<TipoMedioPago, List<Administradora> > ();

		List<Administradora> listaDeAdministradorasTarjetaDeCredito = new ArrayList<Administradora>();
		listaDeAdministradorasTarjetaDeCredito.add(Administradora.Visa);
		listaDeAdministradorasTarjetaDeCredito.add(Administradora.MasterCard);
		mapa.put(TipoMedioPago.TarjetaCredito, listaDeAdministradorasTarjetaDeCredito);

		List<Administradora> adminsTarjetaDebito = new ArrayList<Administradora>();
		adminsTarjetaDebito.add(Administradora.MasterCard);
		adminsTarjetaDebito.add(Administradora.Visa);
		mapa.put(TipoMedioPago.TarjetaDebito, adminsTarjetaDebito);

		List<Administradora> adminsEfectivo = new ArrayList<Administradora>();
		adminsEfectivo.add(Administradora.PagoFacil);
		adminsEfectivo.add(Administradora.RapiPago);
		mapa.put(TipoMedioPago.Efectivo, adminsEfectivo);

		List<Administradora> adminsAtm = new ArrayList<Administradora>();
		adminsAtm.add(Administradora.RedLink);
		mapa.put(TipoMedioPago.ATM, adminsAtm);

		return mapa;

	}



}
