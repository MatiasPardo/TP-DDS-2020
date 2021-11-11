
package geSoc.main;

import java.text.*;
import java.time.LocalDate;
import java.util.*;

import org.uqbarproject.jpa.java8.extras.*;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import org.uqbarproject.jpa.java8.extras.transaction.*;

import com.github.jknack.handlebars.internal.*;

import geSoc.calendarizacion.*;
import geSoc.compra.*;
import geSoc.excepcion.*;
import geSoc.model.*;
import geSoc.organizacion.*;
import geSoc.reporte.*;
import geSoc.validadores.*;



public class Inicializar extends AbstractPersistenceTest implements WithGlobalEntityManager  {

	public static void main(String[] args) {
		/*
		 *
		}*/
		Inicializar miMenu = new Inicializar();
		
		for(Usuarie user: miMenu.init()){
			for(NotificacionDeOperacionDeEgreso not: user.getNotificaciones()){
				not.NotificarResultado(new ImprimirPorPantalla());
			}
		}
	  }
	
	public List<Usuarie> init () {

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
		Usuarie usuario2;
		Usuarie usuario3;
		Usuarie usuario4;
		Usuarie usuario5;
		List<Usuarie> usuariosRevisoresDeCompra = new ArrayList<Usuarie>();
		MedioDePago medioDePago1;
		Presupuesto presupuesto1;
		Presupuesto presupuesto2;
		Presupuesto presupuesto3;
		Categoria judicial;
		Categoria agropecuaria;
		Categoria ong;
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
		//withTransaction(() -> {});

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
		agropecuaria = new IndustriaAgropecuaria(montoMaximo);
		judicial = new Judiciales(true);
		List<EntidadBase> entidadBloqueadas = new LinkedList<EntidadBase>();
		EntidadBase entidadABloquear = new EntidadBase("entidadBloqueada","esta es una entidad que no se puede agregar");
		entidadBloqueadas.add(entidadABloquear);
		ong = new ONG(entidadBloqueadas);
		entidadJuridica1 = new EntidadJuridica(agropecuaria,"razonSocial1", "entidadJuridica1", "33-70842639-9", "siempreViva1234", "IncriptoIGJ", entidades, TipoEntidad.EMPRESA);
		//ENTIDADES PERSISTENCIA
		entityManager().persist(entidadBase1);
		entityManager().persist(entidadBase2);
		entityManager().persist(agropecuaria);

		entityManager().persist(entidadJuridica1);
		entityManager().persist(ong);
		entityManager().persist(judicial);	 
		entityManager().persist(entidadABloquear);

		//DOCUMENTO
		documento1 = new Documento();
		entityManager().persist(documento1);

		//PROVEEDOR
		proveedor1 = new Proveedor();
		entityManager().persist(proveedor1);

		
		//USUARIOS
		usuario1 = new Usuarie ("s", "ContraseaPulenta80!.", entidadJuridica1 ,true);
		usuario2 = new Usuarie ("maria", "ContraseaPulenta80!.", entidadJuridica1 ,true);
		usuario3 = new Usuarie ("jose", "ContraseaPulenta80!.", entidadJuridica1 ,true);
		usuario4 = new Usuarie ("fernando", "ContraseaPulenta80!.", entidadJuridica1 ,true);
		usuario5 = new Usuarie ("marcos", "ContraseaPulenta80!.", entidadJuridica1 ,true);
		entityManager().persist(usuario1);
		entityManager().persist(usuario2);
		entityManager().persist(usuario3);
		entityManager().persist(usuario4);
		entityManager().persist(usuario5);

	    
		//OPERACION EGRESO
		usuariosRevisoresDeCompra.add(usuario1);
		operacionDeEgreso1 = new OperacionDeEgreso(LocalDate.now(), "Televisores", CriterioPresupuesto.NINGUNO,usuariosRevisoresDeCompra);
		OperacionDeEgreso operacionDeEgreso2 = new OperacionDeEgreso(LocalDate.now(), "Heladeras", CriterioPresupuesto.NINGUNO,usuariosRevisoresDeCompra);

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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String inicioConHora1 = "2020-12-13 19:10:00";
		String inicioConHora2 = "2020-12-13 19:20:00";
		String finConHora = "2021-12-31 15:33:23";

		Date inicio = new Date();
		Date inicio2 = new Date();
		Date fin = new Date();
		try {
			inicio = sdf.parse(inicioConHora1);
			fin = sdf.parse(finConHora); 
			inicio2 = sdf.parse(inicioConHora2);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 

	    Periodo periodo = new Periodo(inicio, fin , 5, 166);	
	    Periodo periodo2 = new Periodo(inicio2, fin , 5, 100);	
	    List<OperacionDeEgreso> operaciones2 = new LinkedList<OperacionDeEgreso>();
		operacionesDeEgreso.add(operacionDeEgreso1);
		operaciones2.add(operacionDeEgreso2);
	    Programacion miTarea = new Programacion("primerJob",operacionesDeEgreso, periodo);
	    Programacion miTarea2 = new Programacion("segundoJob",operaciones2, periodo2);
	    entityManager().persist(operacionDeEgreso1);
	    entityManager().persist(operacionDeEgreso2);
	   
	    entityManager().persist(periodo);
	    entityManager().persist(periodo2);
	    entityManager().persist(miTarea);
	    entityManager().persist(miTarea2);

		withTransaction(() -> {	});
		
	    /*******CARGA TAREA PROGRAMADA PARA EL REPO************/

		//SE TERMINA LA CARGA DE DATOS.
	    
		return usuariosRevisoresDeCompra;
	}

}

