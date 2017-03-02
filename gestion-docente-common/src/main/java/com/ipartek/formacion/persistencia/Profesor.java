package com.ipartek.formacion.persistencia;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "profesor")
public class Profesor implements Serializable {
	
	private static final long serialVersionUID = -6698866485450376235L;
	public static final int CODIGO_NULO = -1;

	
	
	@Id
	@GeneratedValue
	@Column(name = "codigo")
	private long codigo;
    private String dni;
	private String nombre;
	private String apellidos;
	private String nSS;
	private Date fNacimiento;

	private String email;
	private String direccion;
	private String poblacion;
	private Integer codigopostal;
	
	private String telefono;
	private boolean activo;
	@OneToMany( fetch = FetchType.LAZY, mappedBy="profesor")
	//@JoinColumn(name ="codigo", referencedColumnName= "profesor_codigo")
	private Set<Imparticion> imparticiones;
	
	public Profesor() {
		super();
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
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
	public String getnSS() {
		return nSS;
	}
	public void setnSS(String nSS) {
		this.nSS = nSS;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Profesor [codigo=" + codigo + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", nSS=" + nSS + ", fNacimiento=" + fNacimiento + ", email=" + email + ", direccion=" + direccion
				+ ", poblacion=" + poblacion + ", codigopostal=" + codigopostal + ", telefono=" + telefono + ", activo="
				+ activo + ", imparticiones=" + imparticiones + "]";
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
		Profesor other = (Profesor) obj;
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
