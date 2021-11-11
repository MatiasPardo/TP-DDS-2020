package geSoc.apis;

import java.util.*;

import geSoc.compra.*;
import geSoc.excepcion.*;
import geSoc.model.*;

public class RepoDeMonedas {
	
	private List<Moneda> monedas = new LinkedList<Moneda>();

	public List<Moneda> getMonedas() {
		return monedas;
	}

	public void setMonedas(List<Moneda> monedas) {
		this.monedas = monedas;
	}
	
}
