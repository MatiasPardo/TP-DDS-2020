package geSoc.calendarizacion;


import java.util.*;

import javax.persistence.*;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.uqbarproject.jpa.java8.extras.*;
import org.uqbarproject.jpa.java8.extras.transaction.*;

import geSoc.model.*;
import geSoc.validadores.*;

public class EjecutorDeTarea implements Job, WithGlobalEntityManager,TransactionalOps{

	private RepositorioDeOperacionesPendientes elRepo = new RepositorioDeOperacionesPendientes();

	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		Programacion miProgramacion= (Programacion) ctx.getMergedJobDataMap().get("miProgramacion");
		
		this.cargarValidadores(miProgramacion);
		this.cargarOperaciones(miProgramacion);
		
		try{				
			for(NotificacionDeOperacionDeEgreso not: elRepo.validarOperaciones()){
				not.NotificarResultado(new ImprimirPorPantalla());
				entityManager().persist(not);
			}
			withTransaction(() -> {	});
			
			System.out.println("*********finalizo una ejecucion del Validar**********\n");	
			
		}catch (Exception e){
			System.out.println(e.toString());	
		}
	}
	
	public void cargarValidadores(Programacion miProgramacion){
		
		ValidadorDeOperacion validador1 = new ValidadorCantidadDePresupuestos();
		ValidadorDeOperacion validador2 = new ValidadorCompraSegunPresupuesto();
		ValidadorDeOperacion validador3 = new ValidadorSegunCriterioSeleccion();
		elRepo.agregarValidadores(validador1);
		elRepo.agregarValidadores(validador2);
		elRepo.agregarValidadores(validador3);
		
	}

	public void cargarOperaciones(Programacion miProgramacion){
		List<OperacionDeEgreso> operacionesRefresh = new LinkedList<OperacionDeEgreso>();
		for(OperacionDeEgreso op: miProgramacion.getOperacionesPendientes()){
			operacionesRefresh.add(entityManager().merge(op));
		}
		elRepo.setOperacionesPendientes(operacionesRefresh);
	}

}
