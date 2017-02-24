package com.ipartek.formacion.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@Table
public class Curso implements Serializable{
	
	private static final long serialVErsionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column( name = "codigo")//no hace falta cuando se llaman igual , la variable y la columna
	private long codigo;
	@Column( name = "nombre")
	private String nombre;
	@Column( name = "identificador")
	private String identificador;
	@Column( name = "fInicio")
	private Date fInicio;
	@Column( name = "fFin")
	private Date fFin;
	@Column( name = "nHoras")
	private int nHoras;
	@Column( name = "temario")
	private String temario;
	@Column( name = "precio")
	private double precio;
	

	
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
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Date getfInicio() {
		return fInicio;
	}
	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}
	public Date getfFin() {
		return fFin;
	}
	public void setfFin(Date fFin) {
		this.fFin = fFin;
	}
	public int getnHoras() {
		return nHoras;
	}
	public void setnHoras(int nHoras) {
		this.nHoras = nHoras;
	}
	public String getTemario() {
		return temario;
	}
	public void setTemario(String temario) {
		this.temario = temario;
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
		Curso other = (Curso) obj;
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
		return "Curso [codigo=" + codigo + ", nombre=" + nombre + ", identificador=" + identificador + ", fInicio="
				+ fInicio + ", fFin=" + fFin + ", nHoras=" + nHoras + ", temario=" + temario + ", precio=" + precio
				+ "]";
	}
	
	

}
