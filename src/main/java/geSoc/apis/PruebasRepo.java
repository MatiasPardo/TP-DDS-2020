package geSoc.apis;

import java.util.*;

import geSoc.apis.*;
import geSoc.compra.*;
import geSoc.excepcion.*;

public class PruebasRepo {
	
	
	public static void main(String[] args) {
		
		UbicacionMonedaML monedaYUb = new UbicacionMonedaML("ARS");
		List<Moneda> monedas = monedaYUb.monedasValidas();
		for(Moneda moneda: monedas){
			System.out.println(moneda.getSymbol()+" \t"+moneda.getDescription());
	
		}
	}
}
