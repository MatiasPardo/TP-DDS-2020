package geSoc.repositorios;

import java.util.List;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import geSoc.organizacion.Categoria;
import geSoc.organizacion.EntidadBase;
import geSoc.organizacion.EntidadJuridica;

public class RepositorioEntidades implements WithGlobalEntityManager {

	public static RepositorioEntidades instacia = new RepositorioEntidades();
	
	  public List<EntidadBase> buscarEntidades(String ids) {
	    	List<EntidadBase> entidades = null;

			 try {
				 if(ids == null){
					 entidades = entityManager() //
							 .createQuery("from EntidadBase e ", EntidadBase.class) //
							 .getResultList();
				 }else{
					 entidades = entityManager()
							 .createQuery("from EntidadBase e where e.id in "+ids, EntidadBase.class) //
							 .getResultList();
				 }
				 return entidades;
				 }catch(Exception e) {
					 return null;
				 }
		}

		public List<EntidadJuridica> buscarEntidadesPorCategoria(String nombreCategoria) {
			 try {
				 return entityManager() //
						 .createQuery( "from EntidadJuridica e where e.categoria in (select id from Categoria c where c.nombre = '" + nombreCategoria + "')" , EntidadJuridica.class) //
					     // .setParameter("nombreCategoria", nombreCategoria ) //
					     .getResultList();
				 }catch(Exception e) {
					 return null;
				 }

		}
		
		public Categoria buscarCategoria(String nombre){
			Categoria categoria = null;
			try{
				categoria = entityManager()
						.createQuery("from Categoria c where c.nombre = '" + nombre + "'",Categoria.class)
						.getResultList().get(0);
				return categoria;
			}catch (Exception e){
				return categoria;
			}
		}
		
		public List<Categoria> buscarCategorias() {
			 try {
				 return entityManager() //
				        .createQuery("from Categoria c ", Categoria.class) //
				        .getResultList();
				 }catch(Exception e) {
					 return null;
				 }
		}

	
}
