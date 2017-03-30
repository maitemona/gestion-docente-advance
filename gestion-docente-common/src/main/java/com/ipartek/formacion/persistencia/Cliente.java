package com.ipartek.formacion.persistencia;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name ="cliente")
@Table(name ="cliente")
@NamedQueries({
	@NamedQuery(name="cliente.getAll", query="SELECT cli FROM cliente as cli")
})
public class Cliente  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	 
	@Id
	@GeneratedValue
	private long codigo;
	private String nombre;
	private boolean activo;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="cliente")
	private Set<Curso> cursos;
	
	
	public Set<Curso> getCursos() {
		return cursos;
	}
	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
	
	

}
