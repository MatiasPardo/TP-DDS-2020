package geSoc.calendarizacion;


import static org.quartz.TriggerBuilder.newTrigger;

import java.sql.*;
import java.util.*;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;
import org.quartz.*;
import org.quartz.ee.servlet.*;
import org.quartz.impl.StdSchedulerFactory;
import org.uqbarproject.jpa.java8.extras.*;
import org.uqbarproject.jpa.java8.extras.transaction.*;

import geSoc.excepcion.*;
import geSoc.model.*;




public class InitializerQuartz extends QuartzInitializerServlet implements WithGlobalEntityManager {

	private static InitializerQuartz inicializador;
	
	public static SchedulerFactory factory = new org.quartz.impl.StdSchedulerFactory();

	
	public static InitializerQuartz getInstance(){
		if(inicializador == null){
			inicializador = new InitializerQuartz();
		}
		return inicializador;
	}
	
	public void init() {
		try {
			Scheduler quartzScheduler = InitializerQuartz.factory.getScheduler();

			List<Programacion> tareas = this.getTareasActivas();
			for(Programacion tarea: tareas) {
				Trigger tb = calendarizarTrigger(tarea.getIdentificador(), tarea.getPeriodo());
				if(tb != null){
					JobDetail job = this.createJob(tarea);
					quartzScheduler.scheduleJob(job, tb);
				}
			}
			quartzScheduler.start();
		} catch (NoResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public static void programarTarea(Programacion tarea) {

		JobDetail job = getInstance().createJob(tarea);
		Trigger trigger = getInstance().calendarizarTrigger(tarea.getIdentificador(),tarea.getPeriodo()); 

		//schedule it
		try {
			Scheduler quartzScheduler = InitializerQuartz.factory.getScheduler();
			quartzScheduler.scheduleJob(job, trigger);
			quartzScheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tarea.setEstaActiva(true);
	}
	
	private JobDetail createJob(Programacion tarea){
		JobDetail job = JobBuilder.newJob(EjecutorDeTarea.class)
				.withIdentity(tarea.getIdentificador()).build();
		job.getJobDataMap().put("miProgramacion",tarea);
		return job;
	}
	
	@SuppressWarnings("static-access")
	private Trigger calendarizarTrigger(String jobId, Periodo periodo) {
		TriggerBuilder<Trigger> tb = newTrigger().withIdentity(jobId).forJob(jobId);
		SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
		if(periodo.getFechaInicio().before(new Date())){
			tb.startAt(new Date());
		}else tb.startAt(periodo.getFechaInicio());
				
	    tb.endAt(periodo.getFechaFin());
	    if(periodo.getCantidadRepeticiones() > 0){
	    	simpleScheduleBuilder = simpleScheduleBuilder.repeatSecondlyForTotalCount(periodo.getCantidadRepeticiones()); 
	    }else{
	    	simpleScheduleBuilder = simpleScheduleBuilder.withIntervalInHours(24); 
	    }
	    simpleScheduleBuilder = simpleScheduleBuilder.withIntervalInSeconds(periodo.getIntervaloRepeticion());
	    tb.withSchedule(simpleScheduleBuilder);
	    return tb.build();	 
		
	}

	public static void eliminarTarea(Programacion tarea){
		String id = String.valueOf(tarea.getId());
        try {
        	StdSchedulerFactory factory = (StdSchedulerFactory) InitializerQuartz.factory;
			Scheduler quartzScheduler = factory.getScheduler("QuartzInitializer");
		    if(quartzScheduler.checkExists(new JobKey(id))) {
    			quartzScheduler.deleteJob(new JobKey(id));
    			tarea.setEstaActiva(false);
    		}
        } catch (NoResultException e) {
		} catch (SchedulerException e) {
		}
	}
	
	public List<Programacion> getTareasActivas(){
		List<Programacion> tareas = new LinkedList<Programacion>();
		try{
			tareas = entityManager()
					.createQuery("from Programacion o where o.estaActiva = '" + true + "'",Programacion.class)
					.getResultList();
			return tareas;
		}catch (Exception e){
			return tareas;
		}
	}
	
}
