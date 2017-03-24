package com.ipartek.formacion.persistencia;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "imparticion" )
public class Imparticion  implements Serializable {

	private static final long serialVersionUID = -6698866485450376235L;
	@Id
	@GeneratedValue
	private long codigo;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profesor_codigo" , referencedColumnName = "codigo")
	private Profesor profesor;
	
	@ManyToMany(fetch = FetchType.EAGER,mappedBy="imparticiones")
	private Set<Alumno> alumnos;
	
	
	
	
	public Imparticion() {
		super();
	}
	
	
	public Set<Alumno> getAlumnos() {
		return alumnos;
	}


	public void setAlumnos(Set<Alumno> alumnos) {
		this.alumnos = alumnos;
	}


	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	@Override
	public String toString() {
		return "Imparticion [codigo=" + codigo + ",profesor=" + profesor + "]";
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
		boolean iguales = false;
		Imparticion other = (Imparticion) obj;
		if (this == obj || obj == null ||getClass() != obj.getClass()||codigo != other.codigo)
		{
			iguales = false;
		}
		else{
			
			iguales = true;
		}
		return iguales;
	}
	
	
	
}
