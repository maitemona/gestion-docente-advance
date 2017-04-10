package com.ipartek.formacion.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.service.interfaces.AlumnoService;

/**
 * Se ejcutan en orden de escritura
 * @author Maite Monasterio
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-test.xml")
public class AlumnoServiceTest {

        @Autowired
        AlumnoService aS;
        
        Alumno alumno;
        List<Alumno> alumnos;
        @Before
        public void setUp() throws Exception {
            alumno = new Alumno();
            alumno.setNombre("Jon Koldobika");
            alumno.setApellidos("ajuriagogeaskoa Belaustegigoitia");
            alumno.setDni("12345678d");
            alumno.setCodigopostal(48991);
        }
        
        @After
        public void tearDown() throws Exception {
            alumno = null;
        }
        
        
        @Test //test unitario 
        public void testclase(){    
            assertEquals("class com.ipartek.formacion.service.AlumnoServiceImp", this.aS.getClass().toString());
        }
        
  
        @Test(expected = DuplicateKeyException.class)
        public void create(){
        	
            Alumno alum = aS.create(alumno);
            assertNotNull("El alumno es nulo", alum == null);
            assertTrue("El codigo debe ser mayor que 0", alumno.getCodigo() > 0);
            assertEquals("El nombre no es identico", alum.getNombre(), alumno.getNombre());
            //todo los atributos
            assertEquals("Los datos no es identico", alum,aS.getById(alum.getCodigo()));
           // alum = aS.create(alumno);
           // aS.create(alumno);
            aS.delete(alum.getCodigo());
        }
        
        @Test
        public void deleteTest(){
        	
            Alumno alum = aS.create(alumno);
            aS.delete(alum.getCodigo());
            assertNull("el alumnoo No se ha borrado" ,aS.getById(alum.getCodigo()));
            //assertNull("el alumnoo No se ha borrado"),aS.getById(alum.getCodigo());
        }
        
       

		@Test
        public void getAllTest(){
        	List <Alumno> alumnos = aS.getAll();
        	int size=4;
        	assertEquals("el nº de alumnos incorrecto", size, alumnos.size());
            
        }
    
        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
        }
    
        @AfterClass
        public static void tearDownAfterClass() throws Exception {
        }
    
    
    
        



}
