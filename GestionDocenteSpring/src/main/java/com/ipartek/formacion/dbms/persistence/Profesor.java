package com.ipartek.formacion.dbms.persistence;

import java.util.Date;

public class Profesor{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.codigo+" " + this.getEmail();
	}

	public static final int CODIGO_NULO = -1;
	private long nSS;
	private int codigo;
	private String dni;
	private String nombre;
	private String apellidos;
	private Date fNacimiento;
	private String email;
	private String direccion;
	

	public Profesor() {
		super();
		this.codigo = CODIGO_NULO;
		this.nSS = 0;
		this.email = "";
		this.direccion = "";
		this.apellidos="";
		this.nombre="";
		this.dni="";
		this.fNacimiento = new Date();
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

	public long getnSS() {
		return nSS;
	}

	public void setnSS(long nSS) {
		this.nSS = nSS;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}
