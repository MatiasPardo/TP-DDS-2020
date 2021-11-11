package geSoc.validadores;

import java.util.*;

import javax.persistence.*;

import geSoc.model.*;


public abstract class ValidadorDeOperacion {
	  
	public abstract boolean esOperacionValida(OperacionDeEgreso operacion,List<ResultadoValidacion> resultados);

}
