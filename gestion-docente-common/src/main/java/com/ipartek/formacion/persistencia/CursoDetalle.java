package com.ipartek.formacion.persistencia;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "curso_detalle")
public class CursoDetalle implements Serializable{
	private static final long serialVersionUID = -6698866485450376235L;

	@Id
	@GeneratedValue
	private long codigo;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	
}
