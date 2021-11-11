package geSoc.organizacion;

import geSoc.excepcion.ClasificacionInvalidaException;
import geSoc.model.*;

public enum TipoEntidad {

	EMPRESA {

		@Override
		public Clasificacion getClasificacion(EntidadJuridica entidad) {
			//TODO logica de afip para verificar el tipo de clasificacion
			return Clasificacion.Micro;
		}
	} ,
	OSC {
		@Override
		public Clasificacion getClasificacion (EntidadJuridica entidad) {
			throw new ClasificacionInvalidaException("OSC no posee clasificacion");
		}
	};
	public Clasificacion getClasificacion(EntidadJuridica entidad) {
		return null; 
	}

}
 