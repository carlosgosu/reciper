package es.cjolalla.catalogingredients.usuarios;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="perfil")
public class Perfil implements Serializable{

	@Id
	@Column(name="idPerfil")
	private Long idPerfil;
	
	@Column(name="perfil")
	private String perfil;
	
	@ManyToMany
	@JoinTable(
			  name = "perfil_permiso", 
			  joinColumns = @JoinColumn(name = "idPerfil"), 
			  inverseJoinColumns = @JoinColumn(name = "idPermiso"))
	private Set<Permiso> permisos;

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public Set<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(Set<Permiso> permisos) {
		this.permisos = permisos;
	}
	
	
}
