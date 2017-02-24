package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.ipartek.formacion.persistencia.Curso;
public class Cliente  implements Comparable<Curso>, Serializable{
	
	private static final long serialVersionUID = -669884376235L;
	private  int codigo;
	public static final int CODIGO_NULO = -1;
	private String nombre;
	private String direccion;
	private String poblacion;
	private int codigopostal;
	private String telefono;
	private String email;
	private String identificador;
	private boolean activo;
	//mapa de cursos 
//	private Map<Long, com.ipartek.formacion.persistencia.Curso> cursos;
	private Map<Long, Curso> cursos;
	public Cliente() {
		super();
		this.codigo = CODIGO_NULO;
		this.nombre="";
		this.direccion="";
		this.poblacion="";
		this.codigopostal= 48;
		this.telefono="94....";
		this.email="";
		this.identificador="";
		this.activo = true;
		
		cursos = new HashMap<Long, Curso>();
	}

	
	public Map<Long, Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Map<Long, Curso> cursos) {
		this.cursos = cursos;
	}

	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getCodigo() {
		return codigo;
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
