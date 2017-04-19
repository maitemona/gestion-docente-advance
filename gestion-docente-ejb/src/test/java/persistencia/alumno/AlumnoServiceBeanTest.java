package persistencia.alumno;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ipartek.formacion.alumno.AlumnoServiceBean;
import com.ipartek.formacion.alumno.AlumnoServiceRemote;
import com.ipartek.formacion.persistencia.Alumno;
import com.ipartek.formacion.persistencia.Cliente;
import com.ipartek.formacion.persistencia.Curso;
import com.ipartek.formacion.persistencia.Profesor;



///necesaria la clase arquillian 
@RunWith(Arquillian.class)
public class AlumnoServiceBeanTest {
//construir el deploited
	@Deployment
	public static Archive<?> createDeployment(){
		
		//cargar el archivo xml del pom de nuestro proyecto logico , pq necesitamos al common.
        File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve()
                .withTransitivity().asFile();
       /*	hay que cargar todas las clases delas que alumnos sea dependiente , a mano 
       		despues añadir las librerias(addAsLibraries(files))
       	*/
        
        
        //instancia de archivo para entorno jar
        //JavaArchive ja =
		//instancia de archivo para un entorno web
        WebArchive wa = 
        		ShrinkWrap.create(WebArchive.class,"tets.war").
        		addClass(Alumno.class).
        		addClass(Curso.class).
        		addPackage(AlumnoServiceBean.class.getPackage()).
        		addClass(Profesor.class).
        		addClass(Cliente.class).
        		addAsLibraries(files).addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		return wa;
	}
	
	@EJB
	AlumnoServiceRemote as;
	
	@Test
	public void testIsDeployed(){
		assertNotNull(as);
	}
}
