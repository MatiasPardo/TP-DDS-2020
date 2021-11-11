package geSoc.organizacion;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.*;

import org.apache.commons.codec.digest.DigestUtils;

import geSoc.model.*;
import geSoc.validadores.*;

@Entity
public class Usuarie {
	

	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	private String salt;
	private String passHasheada;
	
	@OneToOne
	private EntidadJuridica entidad;
	
	private boolean esAdmin; 
	//con un objeto ganamos el s
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<NotificacionDeOperacionDeEgreso> bandejaDeMensajes = new ArrayList<NotificacionDeOperacionDeEgreso>();

	public Usuarie(){
		
	}
	
	public Usuarie(String usuarie, String pass, EntidadJuridica entidad, boolean esAdmin) {
			PassValidator.isStrong(pass);
			this.entidad = entidad;
			this.salt = asignarSalt();
			this.setNombre(usuarie);
			this.passHasheada = hashPass(pass);
			this.esAdmin = esAdmin;
	}

	public boolean esAdmin () {
		return this.esAdmin;
	}

	public String hashPass(String pass) {
		String hashedPass = DigestUtils.sha256Hex(this.salt+pass);
		return hashedPass;
	}

	private String asignarSalt() {
		final Random r = new SecureRandom();
		byte[] salt = new byte[32];
		r.nextBytes(salt);
		String saltString = salt.toString();
		return saltString;
	}

	public String getPassHasheada() {
		return passHasheada;
	}

	public void agregarNotificacion(NotificacionDeOperacionDeEgreso notificacion) {
		bandejaDeMensajes.add(notificacion);
	}

	public List<NotificacionDeOperacionDeEgreso> getNotificaciones(){
		return this.bandejaDeMensajes;
	}

	public Long getId() {
		return this.id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
