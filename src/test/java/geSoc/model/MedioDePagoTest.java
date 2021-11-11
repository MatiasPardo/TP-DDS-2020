package geSoc.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import geSoc.compra.*;
import geSoc.excepcion.*;

public class MedioDePagoTest {

	private MedioDePago medioDePago1;
	private MedioDePago medioDePago2;
	@Before
	public void init() {

	}
	@Test
	public void administradoraValidaCreaMedioDePago() {
		medioDePago1 = new MedioDePago(TipoMedioPago.TarjetaCredito,Administradora.Visa,"clave" );

		assertEquals(true,medioDePago1 instanceof MedioDePago );
	}


	@Test(expected = AdministradoraIncorrectaException.class)
	public void exeptionAlCrearMedioDePago() {
		medioDePago2 = new MedioDePago(TipoMedioPago.TarjetaCredito,Administradora.PagoFacil,"clave");

	}



}
