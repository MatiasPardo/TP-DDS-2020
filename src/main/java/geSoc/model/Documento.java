package geSoc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Documento {
	
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return this.id;
	}

}