package com.ipartek.formacion.persistencia;

import java.io.Serializable;
import java.util.Date;

public class Evaluacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5404111911084872480L;

	private long codigo;
	private Date fExamen;
	private int nota;

	public Evaluacion() {
		super();
	}

	/**
	 * @return the codigo
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the fExamen
	 */
	public Date getfExamen() {
		return fExamen;
	}

	/**
	 * @param fExamen
	 *            the fExamen to set
	 */
	public void setfExamen(Date fExamen) {
		this.fExamen = fExamen;
	}

	/**
	 * @return the nota
	 */
	public int getNota() {
		return nota;
	}

	/**
	 * @param nota
	 *            the nota to set
	 */
	public void setNota(int nota) {
		this.nota = nota;
	}

}
