package com.ipartek.formacion.listeners;

import javax.persistence.PostLoad;

import com.ipartek.formacion.persistencia.Profesor;



public class ProfesorListener {
	@PostLoad
	public void profesorPostLoad(Profesor profesor) {
		profesor.setFullName(profesor.getNombre() + " " + profesor.getApellidos());
	}
}