package geSoc.apis;

import geSoc.model.*;

public interface UbicacionYMoneda {
	
	public void cargarDatosCP(Direccion direccion);
	
	public double cotizar(String codigoMoneda1, String codigoMoneda2);

}

  