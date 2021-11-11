package geSoc.apis;

import com.google.gson.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import geSoc.compra.*;
import geSoc.excepcion.*;
import geSoc.model.*;

import java.util.*;

import javax.ws.rs.core.MediaType;


public class UbicacionMonedaML implements UbicacionYMoneda{
	
	private String country;
	
	public UbicacionMonedaML(String country){
		this.country = country;
	}

	@Override
	public void cargarDatosCP(Direccion direccion){
		JsonObject jsonCP = this.codigoPostal(direccion.getCodigoPostal());
		if(jsonCP.get("city").getAsJsonObject().get("name").isJsonNull()){
			direccion.setProvincia(jsonCP.get("state").getAsJsonObject().get("name").getAsString());
			direccion.setCiudad(jsonCP.get("state").getAsJsonObject().get("name").getAsString());
		}
		else if(jsonCP.get("state").getAsJsonObject().get("name") != null){
			direccion.setProvincia(jsonCP.get("state").getAsJsonObject().get("name").getAsString());
			direccion.setCiudad(jsonCP.get("city").getAsJsonObject().get("name").getAsString());
		}
		direccion.setPais(jsonCP.get("country").getAsJsonObject().get("name").getAsString());
	}

	@Override
	public double cotizar(String codigoMoneda1, String codigoMoneda2){
		ClientResponse response;
		response =  Client.create()
			       .resource("https://api.mercadolibre.com/currency_conversions/search")
			       .queryParam("from", codigoMoneda1).queryParam("to", codigoMoneda2)
			       .accept(MediaType.APPLICATION_JSON)
			       .get(ClientResponse.class);
		return new Gson().fromJson(response.getEntity(String.class), JsonObject.class).get("ratio").getAsDouble();//.get("ratio").getAsDouble();
	}
	
	public static JsonArray paises() {
		ClientResponse response;
		response =  Client.create()
			       .resource("https://api.mercadolibre.com/")
			       .path("classified_locations/countries")
			       .accept(MediaType.APPLICATION_JSON) 
			       .get(ClientResponse.class);
		return new Gson().fromJson(response.getEntity(String.class),JsonArray.class);
	}
	
	public JsonObject codigoPostal(String idCP){
		if(this.country != null){
			ClientResponse response;
			response =  Client.create()
				       .resource("https://api.mercadolibre.com/")
				       .path("countries/"+this.country+"/zip_codes/"+idCP)
				       .accept(MediaType.APPLICATION_JSON) 
				       .get(ClientResponse.class);
			String json = response.getEntity(String.class);
			return new Gson().fromJson(json, JsonObject.class);
		}else throw new UbicacionYMonedaExcepcion("Cargar primero el pais en donde se encuentra");
	}		//o en defecto tomar este valor del sistema

	public List<Moneda> monedasValidas() {
		ClientResponse response;
		response =  Client.create()
			       .resource("https://api.mercadolibre.com/")
			       .path("currencies/")
			       .accept(MediaType.APPLICATION_JSON)
			       .get(ClientResponse.class);
		String json = response.getEntity(String.class);
		JsonArray jsonMonedas = new Gson().fromJson(json, JsonArray.class);
		List<Moneda> monedas = new LinkedList<Moneda>();
		for(JsonElement element: jsonMonedas){
			Moneda moneda = new Gson().fromJson(element, Moneda.class);
			monedas.add(moneda);
		}
		return monedas;

	}
	

	//devuelve provincias
/*	public JsonObject informacionDelPais(String idPais){
		ClientResponse response;
		response =  Client.create()
		       .resource("https://api.mercadolibre.com/")
		       .path("classified_locations/countries/"+idPais)
		       .accept(MediaType.APPLICATION_JSON) 
		       .get(ClientResponse.class);
		
		String json = response.getEntity(String.class);
		return new Gson().fromJson(json, JsonObject.class);
	
	}
		
	public JsonObject informacionDelEstado(String idState){
		ClientResponse response;
		response =  Client.create()
		       .resource("https://api.mercadolibre.com/")
		       .path("classified_locations/states/"+idState)
		       .accept(MediaType.APPLICATION_JSON) 
		       .get(ClientResponse.class);
		
		String json = response.getEntity(String.class);
		return new Gson().fromJson(json, JsonObject.class);
	
	}
	public JsonObject informacionDeCiudad(String idCity){
		ClientResponse response;
		response =  Client.create()
		       .resource("https://api.mercadolibre.com/")
		       .path("classified_locations/cities/"+idCity)
		       .accept(MediaType.APPLICATION_JSON) 
		       .get(ClientResponse.class);
		
		String json = response.getEntity(String.class);
		return new Gson().fromJson(json, JsonObject.class);
	
	}
	*/
}