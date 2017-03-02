package com.ipartek.formacion.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "curso_detalle")
public class CursoDetalle implements Serializable{
	private static final long serialVersionUID = -6698866485450376235L;

	@Id
	@GeneratedValue
	private long codigo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "curso_codigo")
	private Curso curso;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "modulo_codigo")
	private Modulo modulo;
	
	private Date fInicio;
	private Date fFin;
	

	@OneToOne(fetch = FetchType.EAGER,mappedBy="cursodetalle")
	private Imparticion imparticion;
	
	
	

	public Imparticion getImparticion() {
		return imparticion;
	}



	public void setImparticion(Imparticion imparticion) {
		this.imparticion = imparticion;
	}



	public CursoDetalle() {
		super();
	}
	
	




	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
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

	@Override
	public String toString() {
		return "CursoDetalle [codigo=" + codigo + ", curso=" + curso + ", modulo=" + modulo + ", fInicio=" + fInicio
				+ ", fFin=" + fFin + "]";
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
		CursoDetalle other = (CursoDetalle) obj;
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
