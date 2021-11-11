package db;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import geSoc.calendarizacion.*;
import geSoc.compra.Administradora;
import geSoc.compra.MedioDePago;
import geSoc.compra.TipoMedioPago;
import geSoc.model.*;
import geSoc.organizacion.*;
import geSoc.reporte.PorProveedor;
import geSoc.reporte.PorTipo;
import geSoc.reporte.TipoEtiqueta;
import geSoc.validadores.ValidadorCantidadDePresupuestos;
import geSoc.validadores.ValidadorCompraSegunPresupuesto;
import geSoc.validadores.ValidadorDeOperacion;
import geSoc.validadores.ValidadorSegunCriterioSeleccion;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
		
	@Before
	public void init() {
		
		Item item1 ;
		Item item2 ;
		Item item3 ;
		Item item4 ;
		Item item5 ;
		Item item6 ;
		Item item7 ;
		Item item8 ;
		Item item9 ;
		List<Item> items = new ArrayList<Item>();
		List<Item> items2 = new ArrayList<Item>();
		List<Item> items3 = new ArrayList<Item>();
		OperacionDeEgreso operacionDeEgreso1 ;
		EntidadJuridica entidadJuridica1 ;
		EntidadBase entidadBase1;
		EntidadBase entidadBase2;
		List<EntidadBase> entidades =  new ArrayList<EntidadBase>();
		Documento documento1;
		Proveedor proveedor1;
		Usuarie usuario1;
		List<Usuarie> usuariosRevisoresDeCompra = new ArrayList<Usuarie>();
		MedioDePago medioDePago1;
		Presupuesto presupuesto1;
		Presupuesto presupuesto2;
		Presupuesto presupuesto3;
		Categoria categoria;
		List<OperacionDeEgreso> operacionesDeEgreso = new ArrayList<OperacionDeEgreso>();

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
		
		//PERSISTENCIA_ITEM
		entityManager().persist(item1);
		entityManager().persist(item2);
		entityManager().persist(item3);
		entityManager().persist(item4);
		entityManager().persist(item5);
		entityManager().persist(item6);
		entityManager().persist(item7);
		entityManager().persist(item8);
		entityManager().persist(item9);
		
		items.add(item1);
		items.add(item2);
		items2.add(item3);
		items2.add(item4);
		items2.add(item5);
		items3.add(item9);
		items3.add(item8);
		//REPOSITORIO
		//ENTIDADES
		entidadBase1 = new EntidadBase("EB1", "soyEntidadBase1");
		entidadBase2 = new EntidadBase("EB2", "soyEntidadBase2");
		entidades.add(entidadBase1);
		entidades.add(entidadBase2);
		Double montoMaximo = 0.0;
		categoria = new IndustriaAgropecuaria(montoMaximo);
		entidadJuridica1 = new EntidadJuridica(categoria,"razonSocial1", "entidadJuridica1", "33-70842639-9", "siempreViva1234", "IncriptoIGJ", entidades, TipoEntidad.EMPRESA);
		//ENTIDADES PERSISTENCIA
		entityManager().persist(entidadBase1);
		entityManager().persist(entidadBase2);
		entityManager().persist(categoria);
		entityManager().persist(entidadJuridica1);
	    assertNotNull(entidadBase1.getId()); 
	    assertNotNull(entidadBase2.getId()); 
	 //   assertNotNull(categoria.getId()); 
	    assertNotNull(entidadJuridica1.getId()); 
	
		//DOCUMENTO
		documento1 = new Documento();
		entityManager().persist(documento1);
	    assertNotNull(documento1.getId()); 
		//PROVEEDOR
		proveedor1 = new Proveedor();
		entityManager().persist(proveedor1);
	    assertNotNull(proveedor1.getId()); 
		
		//USUARIOS
		usuario1 = new Usuarie ("maria", "ContraseaPulenta80!.", entidadJuridica1 ,true);
		entityManager().persist(usuario1);
	    assertNotNull(usuario1.getId()); 
	    
		//OPERACION EGRESO
		usuariosRevisoresDeCompra.add(usuario1);
		operacionDeEgreso1 = new OperacionDeEgreso(LocalDate.now(), "Televisores", CriterioPresupuesto.MENORVALOR,usuariosRevisoresDeCompra);
		operacionesDeEgreso.add(operacionDeEgreso1);
		OperacionDeEgreso operacionDeEgreso2 = new OperacionDeEgreso(LocalDate.now(), "Heladeras", CriterioPresupuesto.MENORVALOR,usuariosRevisoresDeCompra);
		operacionesDeEgreso.add(operacionDeEgreso2);
		
		//MEDIO DE PAGO
		medioDePago1 = new MedioDePago(TipoMedioPago.TarjetaCredito,Administradora.Visa,"clave" );
		
		//PRESUPUESTO
		presupuesto1 = new Presupuesto(documento1, proveedor1, items);
		presupuesto2 = new Presupuesto(documento1, proveedor1, items2);
		presupuesto3 = new Presupuesto(documento1, proveedor1, items3);

		
		operacionDeEgreso1.agregarPresupuesto(presupuesto1);
		operacionDeEgreso1.agregarPresupuesto(presupuesto2);
		operacionDeEgreso1.agregarPresupuesto(presupuesto3);
		presupuesto1.setAceptado(true);

		operacionDeEgreso1.comprar(medioDePago1);
		
		operacionDeEgreso1.agregarEtiqueta(new PorTipo(TipoEtiqueta.Amoblamientos));
		operacionDeEgreso1.agregarEtiqueta(new PorProveedor(proveedor1));
		operacionDeEgreso2.agregarEtiqueta(new PorTipo(TipoEtiqueta.Indumentaria));
		operacionDeEgreso2.agregarEtiqueta(new PorProveedor(proveedor1));

		entityManager().persist(operacionDeEgreso1);
		entityManager().persist(operacionDeEgreso2);
	    assertNotNull(operacionDeEgreso1.getId()); 
	    assertNotNull(operacionDeEgreso2.getId()); 

	    Programacion miTarea = new Programacion();
	    miTarea.setEstaActiva(true);
	    Periodo periodo = new Periodo();	    
	    periodo.setCantidadRepeticiones(4);
	    periodo.setIntervaloRepeticion(4);
	    miTarea.setPeriodo(periodo);
	    /*******CARGA TAREA PROGRAMADA PARA EL REPO************/
	    miTarea.setOperacionesPendientes(operacionesDeEgreso);
	    entityManager().persist(periodo);
	    entityManager().persist(miTarea);
		
	    assertNotNull(periodo.getId());
	    assertNotNull(miTarea.getId());
	    
		//SE TERMINA LA CARGA DE DATOS.
	    
	    
	}

	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}
	
	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {});
	}
	
	@Test
	public void creacionEntidades() {
		Judiciales categoria = new Judiciales(true);
		List<EntidadBase> entidadesBase = new LinkedList<EntidadBase>();
		EntidadBase entidadBase1 = new EntidadBase("nombreFicticio", "description");
		EntidadBase entidadBase2 = new EntidadBase("nombreFicticio2", "description2");
		entidadesBase.add(entidadBase1);
		entidadesBase.add(entidadBase2);
		EntidadJuridica entidadMayor = new EntidadJuridica(categoria, "LameBotas", "Prueba", "2020202020", "1515", "", entidadesBase, TipoEntidad.EMPRESA);
		
		entityManager().persist(entidadBase1);
		entityManager().persist(entidadBase2);
		entityManager().persist(entidadMayor);
		
		//EntidadBase miEntidad1 = entityManager().find(EntidadBase.class, entidadBase1.getId());
		//EntidadBase miEntidad2 = entityManager().find(EntidadBase.class, entidadBase2.getId());
		//EntidadJuridica miEntidadJuridica = entityManager().find(EntidadJuridica.class, entidadMayor.getId());
			
	//	System.out.print("Entidad base:"+entidadBase1.getId());
	//	System.out.print("\nEntidad base:"+entidadBase2.getId());
	//	System.out.print("\nEntidad Mayor:"+entidadMayor.getId()+"\n");

		assertNotNull(entityManager());
	}


}
