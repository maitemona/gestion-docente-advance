package com.ipartek.formacion.service;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.service.interfaces.AlumnoService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-test.xml")
public class AlumnoServiceTest {

        @Autowired
        AlumnoService aS;
        
        Alumno alumno;
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
        
        @Test
        public void create(){
            
        }
    
    
        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
        }
    
        @AfterClass
        public static void tearDownAfterClass() throws Exception {
        }
    
    
    
        



}
