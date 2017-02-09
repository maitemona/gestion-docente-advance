package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;

public class Cliente  implements Comparable<Curso>, Serializable{
	private static final long serialVersionUID = -669884376235L;
	private  int codigo;
	public static final int CODIGO_NULO = -1;
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private String identificador;

	public Cliente() {
		super();
		this.nombre="";
		this.direccion="";
		this.telefono="94....";
		this.email="";
		this.identificador="";
	}





	public int getCodigo() {
		return codigo;
	}





	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}





	public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
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





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getIdentificador() {
		return identificador;
	}





	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}





	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono="
				+ telefono + ", email=" + email + ", identificador=" + identificador + "]";
	}





	@Override
	public int compareTo(Curso o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
