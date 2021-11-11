package geSoc.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import geSoc.apis.*;
import geSoc.compra.*;
import geSoc.excepcion.*;

public class UbicacionYMonedaTest {


/*	@Before
	public void init(){

	}
*/

	@Test
	public void verificarCiudadyProvinciaCABA() {
		Direccion direccion1 = new Direccion("calle falsa","1234","0","1242"); //1242
		direccion1.cargarDatosConApi(new UbicacionMonedaML("AR"));
		String ciudad = new String("Capital Federal");
		assertTrue(direccion1.getCiudad().equals(ciudad) && direccion1.getProvincia().equals(ciudad));
	}

	@Test
	public void verificarCiudadProvincia() {
		Direccion direccion1 = new Direccion("calle falsa","1234","0","1804");
		direccion1.cargarDatosConApi(new UbicacionMonedaML("AR"));
		String provincia = new String("Buenos Aires");
		assertTrue(direccion1.getProvincia().equals(provincia));
	}

	@Test
	public void verificarCotizacionDeMoneda() {
		Moneda monedaPeso = new Moneda();
		monedaPeso.setId("ARS");
		Moneda monedaDolar = new Moneda();
		monedaDolar.setId("USD");
		double cotizacionDelDia = 60.61;
		assertTrue(new UbicacionMonedaML("AR").cotizar(monedaDolar.getId(), monedaPeso.getId()) > cotizacionDelDia);
	}
	
}
