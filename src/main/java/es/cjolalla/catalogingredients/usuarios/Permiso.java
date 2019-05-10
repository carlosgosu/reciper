package es.cjolalla.catalogingredients.usuarios;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Son los roles o authorities
@Entity
@Table(name="permiso")
public class Permiso implements Serializable{

	@Id
	@Column(name="idPermiso")
	private Long idPermiso;
	
	@Column(name="nombrePermiso")
	private String nombrePermiso;

	public Long getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(Long idPermiso) {
		this.idPermiso = idPermiso;
	}

	public String getNombrePermiso() {
		return nombrePermiso;
	}

	public void setNombrePermiso(String nombrePermiso) {
		this.nombrePermiso = nombrePermiso;
	}
	
	
}
