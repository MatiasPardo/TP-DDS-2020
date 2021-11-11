package geSoc.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import geSoc.excepcion.ClasificacionInvalidaException;
import geSoc.organizacion.*;
import geSoc.reporte.*;

public class EntidadJuridicaTest {

	private EntidadJuridica entidadJuridica1 ;
	private EntidadBase entidadBase1;
	private EntidadBase entidadBase2;
	private List<EntidadBase> entidades  =  new ArrayList<EntidadBase>(); 
	private List<EntidadBase> entidadesBloqueadas  =  new ArrayList<EntidadBase>(); 
	private Categoria categoria;
	private EntidadBase entidadBloqueada;


	@Before
	public void init() {
		entidadBase1 = new EntidadBase(null, null);
		entidadBase2 = new EntidadBase(null, null);
		entidadBloqueada = new EntidadBase(null, null);
		entidades.add(entidadBase1);
		entidades.add(entidadBase2);
		entidadesBloqueadas.add(entidadBloqueada);
		categoria = new ONG(entidadesBloqueadas);
		entidadJuridica1 = new EntidadJuridica(categoria,"razonSocial1", "entidadJuridica1", "33-70842639-9",
											"siempreViva1234", "IncriptoIGJ", entidades, TipoEntidad.EMPRESA);
	}
	@Test
	public void verificar1234() {
		EntidadJuridica entidadJuridica2 = new EntidadJuridica(categoria,"razonSocial2", "entidadJuridica2", "33-70842639-9",
				"siempreViva1234", "IncriptoIGJ", entidades, TipoEntidad.EMPRESA);
		entidadJuridica2.filtrarPorTipo(TipoEtiqueta.PorProveedor);
		Assert.assertEquals(Clasificacion.Micro, entidadJuridica2.getClasificacion());
	}

	@Test
	public void verificarClasificionValida() {
		EntidadJuridica entidadJuridica2 = new EntidadJuridica(categoria,"razonSocial2", "entidadJuridica2", "33-70842639-9",
				"siempreViva1234", "IncriptoIGJ", entidades, TipoEntidad.EMPRESA);
		Assert.assertEquals(Clasificacion.Micro, entidadJuridica2.getClasificacion());
	}

	@Test(expected = ClasificacionInvalidaException.class)
	public void exceptionOSCnoTieneClasificacion() {
		EntidadJuridica entidadJuridica3 = new EntidadJuridica(categoria,"razonSocial3", "entidadJuridica3", "33-70842639-9",
				"siempreViva1234", "IncriptoIGJ", entidades, TipoEntidad.OSC);
		Assert.assertEquals(Clasificacion.MedianaTramo2, entidadJuridica3.getClasificacion());
	}
	
	
	
}
