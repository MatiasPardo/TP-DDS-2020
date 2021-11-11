package geSoc.repositorios;

import java.util.*;

import org.uqbarproject.jpa.java8.extras.*;
import geSoc.model.*;

public class RepositorioOperaciones  implements WithGlobalEntityManager {

	public static RepositorioOperaciones instacia = new RepositorioOperaciones();

		public List<OperacionDeEgreso> buscarOperacionesPendientes(){
			List<OperacionDeEgreso> operaciones = null;
			try{
				operaciones = entityManager()
						.createQuery("from OperacionDeEgreso o where o.pendiente = '" + true + "'",OperacionDeEgreso.class)
						.getResultList();
				return operaciones;
			}catch (Exception e){
				return operaciones;
			}
		}
		

	
}
