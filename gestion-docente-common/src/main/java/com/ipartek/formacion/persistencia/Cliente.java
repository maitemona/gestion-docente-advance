package com.ipartek.formacion.persistencia;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Cliente  implements Serializable{
	
	private static final long serialVErsionUID = 2L;
	 
	@Id
	@GeneratedValue
	private long codigo;
	private String nombre;
	

}
