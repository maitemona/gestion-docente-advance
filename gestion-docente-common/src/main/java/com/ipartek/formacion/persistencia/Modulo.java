package com.ipartek.formacion.persistencia;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "modulo")//si llama igual no hace falta ponerlo
public class Modulo implements Serializable{
	
	private static final long serialVErsionUID = 3L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column( name = "codigo")//no hace falta cuando se llaman igual , la variable y la columna
	private long codigo;
	@Column( name = "nombre")
	private String nombre;
	@Column( name = "nHoras")
	private int nHoras;
	@Column( name = "descripcion")
	private String descripcion;
	@Column( name = "precio")
	private double precio;
	
	
	/*relacion de un modulo con los cursos :q modulo se ha dado en que curso*/
	@OneToMany( fetch = FetchType.LAZY,mappedBy="modulo")
	private Set<CursoDetalle> detalle;
	
	
	
	public Modulo() {
		super();
	}
	public Set<CursoDetalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(Set<CursoDetalle> detalle) {
		this.detalle = detalle;
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
	public int getnHoras() {
		return nHoras;
	}
	public void setnHoras(int nHoras) {
		this.nHoras = nHoras;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
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
		Modulo other = (Modulo) obj;
		if (this == obj || obj == null ||getClass() != obj.getClass()||codigo != other.codigo)
		{
			iguales = false;
		}
		else{
			
			iguales = true;
		}
		return iguales;
	}
	@Override
	public String toString() {
		return "Modulo [codigo=" + codigo + ", nombre=" + nombre + ", nHoras=" + nHoras + ", descripcion=" + descripcion
				+ ", precio=" + precio + "]";
	}
	
	

}
