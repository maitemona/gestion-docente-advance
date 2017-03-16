package com.ipartek.formacion.api.restfulservers.alumno.configuration;
import javax.servlet.Filter;

/**
 * clase para controlar el acceso a mi servicio web rest
 */
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.ipartek.formacion.api.restfulservers.CORSFilter;

public class AlumnoRestInitializar{

	protected Class<?>[] getRootConfigClasses() {
		
		return new Class[]{AlumnoRestControllerConfiguration.class};
	}


	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}


	protected String[] getServletMappings() {
		return new String[]{"/"};
	}


	protected Filter[] getServletFilters() {
		Filter[] filter = {new CORSFilter() };
		return filter;
	}

}
