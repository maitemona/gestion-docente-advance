package com.ipartek.formacion.persistencia;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "alumno")
@Table(name = "alumno")
@NamedQueries({
	@NamedQuery(name="alumno.getAll", query="SELECT a FROM alumno as a")
})

public class Alumno implements Serializable{
	
	private static final long serialVersionUID = -6698866485450376235L;
	
	public static final int CODIGO_NULO = -1;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private long codigo;
	private boolean activo;
	private int nHermanos;
	
	private String dni;
	private String nombre;
	private String apellidos;
	
	private Date fNacimiento;
	private String email;
	private String direccion;
	private String telefono;
	private String poblacion;
	private Integer codigopostal;
	
	@Transient
	private List<Curso> cursos;
 	
	
	public Alumno(){
		super();
	}
	
	
	public List<Curso> getCursos() {
		return cursos;
	}


	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}


	public void setCodigopostal(Integer codigopostal) {
		this.codigopostal = codigopostal;
	}


	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public int getnHermanos() {
		return nHermanos;
	}
	public void setnHermanos(int nHermanos) {
		this.nHermanos = nHermanos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Date getfNacimiento() {
		return fNacimiento;
	}
	public void setfNacimiento(Date fNacimiento) {
		this.fNacimiento = fNacimiento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public int getCodigopostal() {
		return codigopostal;
	}
	public void setCodigopostal(int codigopostal) {
		this.codigopostal = codigopostal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Alumno other = (Alumno) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Alumno [codigo=" + codigo + ", activo=" + activo + ", nHermanos=" + nHermanos + ", dni=" + dni
				+ ", nombre=" + nombre + ", apellidos=" + apellidos + ", fNacimiento=" + fNacimiento + ", email="
				+ email + ", direccion=" + direccion + ", telefono=" + telefono + ", poblacion=" + poblacion
				+ ", codigopostal=" + codigopostal + "]";
	}
	
	

}
