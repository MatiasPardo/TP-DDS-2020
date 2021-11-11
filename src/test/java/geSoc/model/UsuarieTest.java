package geSoc.model;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import geSoc.excepcion.ExceptionSecurityLow;
import geSoc.organizacion.*;

public class UsuarieTest {


	private EntidadJuridica entidadJuridica1 ;
	private EntidadBase entidadBase1;
	private EntidadBase entidadBase2;
	private List<EntidadBase> entidades =  new ArrayList<EntidadBase>();
	private Usuarie usuario1;
	private Categoria categoria;

	@Before
	public void init(){
		entidadBase1 = new EntidadBase(null, null);
		entidadBase2 = new EntidadBase(null, null);
		entidades.add(entidadBase1);
		entidades.add(entidadBase2);
		categoria = new Judiciales(true);
		entidadJuridica1 = new EntidadJuridica(categoria,"razonSocial1", "entidadJuridica1", "33-70842639-9", "siempreViva1234", "IncriptoIGJ", entidades, TipoEntidad.EMPRESA);
		usuario1 = new Usuarie ("maria", "ContraseaPulenta80!.", entidadJuridica1,true);
	}

	@Test
	public void verificarHasheosDePass() {
		Usuarie usuario2 = new Usuarie ("Juan", "JavaPorSiempre2.0", entidadJuridica1,true);
		assertFalse(usuario2.getPassHasheada() == "JavaPorSiempre2.0");
	}

	@Test
	public void VerificarContraseniaCorrecta(){
		Usuarie usuario2 = new Usuarie ("Gonza", "DiseñoSistemas202@", entidadJuridica1,true);
		assertFalse(usuario2.getPassHasheada() == "DiseñoSistemas202");
	}

	@Test (expected = ExceptionSecurityLow.class)
	public void IntentoDeCreacionConPassDebilFalla(){
		Usuarie usuario2 = new Usuarie ("Robertito", "DiseñoSistemas202", entidadJuridica1,true);
	}


}
