package com.ipartek.formacion.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipartek.formacion.curso.CursoServiceRemote;

import com.ipartek.formacion.persistencia.Curso;
import com.ipartek.formacion.service.interfaces.CursoService;



@Service("cursoServiceImp")//le pongo el nombre del beans que tengo en el xml
public class CursoServiceImp implements CursoService{
	
	@Resource(name = "cursoServiceRemote")//siempre se accede asi por si acaso
	private CursoServiceRemote cursoServiceRemote;
	
	@Override
	public void setCursoServiceRemote(CursoServiceRemote cursoService) {
		this.cursoServiceRemote= cursoService;
		
	}

	@Override
	public Curso getById(long codigo) {
		return cursoServiceRemote.getById(codigo);
		
	}

	@Override
	public List<Curso> getAll() {
		return cursoServiceRemote.getAll();
	}

	@Override
	public Curso create(Curso curso) {
		return cursoServiceRemote.create(curso);
	}

	@Override
	public Curso update(Curso curso) {
		return cursoServiceRemote.update(curso);
	}

	@Override
	public void delete(long codigo) {
		 cursoServiceRemote.delete(codigo);
		
	}

	@Override
	public Curso verInforme(int codigo) {
		return cursoServiceRemote.verInforme(codigo);
		
	}

}
