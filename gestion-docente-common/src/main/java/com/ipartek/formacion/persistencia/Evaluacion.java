package com.ipartek.formacion.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "evaluacion")
public class Evaluacion implements Serializable{
	private static final long serialVersionUID = -6698866485450376235L;

	
	@Id
	@GeneratedValue
	private long codigo;
	private Date fExamen;
	private int nota;
	
	
	
	public Evaluacion() {
		super();
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public Date getfExamen() {
		return fExamen;
	}
	public void setfExamen(Date fExamen) {
		this.fExamen = fExamen;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
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
		Evaluacion other = (Evaluacion) obj;
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
		return "Evaluacion [codigo=" + codigo + ", fExamen=" + fExamen + ", nota=" + nota + "]";
	}
	
	
	
}
