package geSoc.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import geSoc.compra.*;
import geSoc.organizacion.*;
import geSoc.reporte.*;

public class OperacionDeEgresoTest {

	private Item item1 ;
	private Item item2 ;
	private Item item3 ;
	private Item item4 ;
	private Item item5 ;
	private Item item6 ;
	private Item item7 ;
	private Item item8 ;
	private Item item9 ;
	private List<Item> items = new ArrayList<Item>();
	private List<Item> items2 = new ArrayList<Item>();
	private List<Item> items3 = new ArrayList<Item>();
	private OperacionDeEgreso operacionDeEgreso1 ;
	private EntidadJuridica entidadJuridica1 ;
	private EntidadBase entidadBase1;
	private EntidadBase entidadBase2;
	private List<EntidadBase> entidades =  new ArrayList<EntidadBase>();
	private Documento documento1;
	private Proveedor proveedor1;
	private Usuarie usuario1;
	private List<Usuarie>usuariosRevisoresDeCompra = new ArrayList<Usuarie>();
	private MedioDePago medioDePago1;
	private Presupuesto presupuesto1;
	private Presupuesto presupuesto2;
	private Presupuesto presupuesto3;
	private Categoria categoria;
	
	
	@Before
	public void init() {
		//ITEMS
		item1 = new Item("lapiceras", "azules"); //total 1000
		item2 = new Item("mate", "de madera");	//total 400
		item1.setPrecioUnitario(100);
		item1.setCantidad(10);
		item2.setPrecioUnitario(200);
		item2.setCantidad(2);
		item3 = new Item("compu", "acer", 1, 30000);
		item4 = new Item("item4", "de madera", 1 , 40000 );
		item5 = new Item("item5", "de madera", 1 , 50000 );
		item6 = new Item("item6", "de madera", 1 , 60000 );
		item7 = new Item("item7", "de madera", 1 , 70000 );
		item8 = new Item("item8", "de madera", 1 , 80000 );
		item9 = new Item("item9", "de madera", 1 , 90000 );
		items.add(item1);
		items.add(item2);
		items2.add(item3);
		items2.add(item4);
		items2.add(item5);
		items3.add(item9);
		items3.add(item8);
		//ENTIDADES
		entidadBase1 = new EntidadBase("EB1", "soyEntidadBase1");
		entidadBase2 = new EntidadBase("EB2", "soyEntidadBase2");
		entidades.add(entidadBase1);
		entidades.add(entidadBase2);
		categoria = new Judiciales(true);
		entidadJuridica1 = new EntidadJuridica(categoria,"razonSocial1", "entidadJuridica1", "33-70842639-9", "siempreViva1234", "IncriptoIGJ", entidades, TipoEntidad.EMPRESA);
		//DOCUMENTO
		documento1 = new Documento();
		//PROVEEDOR
		proveedor1 = new Proveedor();
		//USUARIOS
		usuario1 = new Usuarie ("maria", "ContraseaPulenta80!.", entidadJuridica1,true);
		usuariosRevisoresDeCompra.add(usuario1);
		//OPERACION EGRESO
		operacionDeEgreso1 = new OperacionDeEgreso(LocalDate.now(), "televisores", CriterioPresupuesto.MENORVALOR,usuariosRevisoresDeCompra);
		//MEDIO DE PAGO
		medioDePago1 = new MedioDePago(TipoMedioPago.TarjetaCredito,Administradora.Visa,"clave" );
		//PRESUPUESTO
		presupuesto1 = new Presupuesto(documento1, proveedor1, items);
		presupuesto2 = new Presupuesto(documento1, proveedor1, items2);
		presupuesto3 = new Presupuesto(documento1, proveedor1, items3);


	}

	@Test
	public void filtroAmoblamiento() {
		operacionDeEgreso1.agregarEtiqueta(new PorTipo(TipoEtiqueta.Amoblamientos));
		operacionDeEgreso1.agregarEtiqueta(new PorProveedor(proveedor1));
		EntidadJuridica entidadJuridica2 = new EntidadJuridica(categoria,"razonSocial2", "entidadJuridica2", "33-70842639-9",
				"siempreViva1234", "IncriptoIGJ", entidades, TipoEntidad.EMPRESA);
		entidadJuridica2.agregarOperacionDeEgreso(operacionDeEgreso1);
		List<OperacionDeEgreso> operacionesFiltradas = entidadJuridica2.filtrarPorTipo(TipoEtiqueta.Amoblamientos);

		Assert.assertEquals( 1, operacionesFiltradas.size());
	}
	
	@Test
	public void filtroProveedor() {
		operacionDeEgreso1.agregarEtiqueta(new PorTipo(TipoEtiqueta.Amoblamientos));
		operacionDeEgreso1.agregarEtiqueta(new PorProveedor(proveedor1));
		EntidadJuridica entidadJuridica2 = new EntidadJuridica(categoria,"razonSocial2", "entidadJuridica2", "33-70842639-9",
				"siempreViva1234", "IncriptoIGJ", entidades, TipoEntidad.EMPRESA);
		entidadJuridica2.agregarOperacionDeEgreso(operacionDeEgreso1);
		List<OperacionDeEgreso> operacionesFiltradas = entidadJuridica2.filtrarPorProveedor(proveedor1);

		Assert.assertEquals( 1, operacionesFiltradas.size());
	}

	@Test
	public void verificarImporteDeLaCompra() {
		operacionDeEgreso1.agregarPresupuesto(presupuesto1);
		operacionDeEgreso1.agregarPresupuesto(presupuesto2);
		operacionDeEgreso1.agregarPresupuesto(presupuesto3);
		presupuesto1.setAceptado(true);
		operacionDeEgreso1.comprar(medioDePago1);

		assertEquals(1400, operacionDeEgreso1.getCompra().getImporte(), 1);
		//assertTrue(operacionDeEgreso1.getCompra() instanceof Compra);
	}

	@Test
	public void obtenerPresupuestoMasBajo() {
		operacionDeEgreso1.agregarPresupuesto(presupuesto1);
		operacionDeEgreso1.agregarPresupuesto(presupuesto2);
		operacionDeEgreso1.agregarPresupuesto(presupuesto3);
		assertEquals(presupuesto1.getPrecioTotal(),operacionDeEgreso1.obtenerPresupuestoMasBajo().getPrecioTotal(),1);
	}

}
