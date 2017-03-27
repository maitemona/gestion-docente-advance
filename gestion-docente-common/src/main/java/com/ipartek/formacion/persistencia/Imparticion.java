package com.ipartek.formacion.persistencia;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name = "imparticion")
@Table(name = "imparticion")
public class Imparticion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long codigo;

	public Imparticion() {
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

}
