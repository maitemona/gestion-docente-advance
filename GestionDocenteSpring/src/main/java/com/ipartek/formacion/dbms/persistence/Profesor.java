package com.ipartek.formacion.dbms.persistence;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.ipartek.formacion.dbms.persistence.validator.Phone;

public class Profesor{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.codigo+" " + this.getEmail();
	}

	public static final int CODIGO_NULO = -1;
	private long nSS;
	@NotNull
	@NotBlank
	private int codigo;
	/*validacion standar de java, las anotaciones son de java las implementacion es de hibernate*/
    @Pattern(regexp = "[0-9]{9}[a-z-A-A]", message = "DNi incorrecto")
    private String dni;
    @Size(min=3,max=50)
	private String nombre;
    @Size(min=7,max=150)
	private String apellidos;
    
    /*le pasamos el pattern de fecha y le decimo q se*/
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past
	private Date fNacimiento;
	private String email;
	private String direccion;
	/*creamos una anotacion dentro de la estructura java:@Phone
	 * hay q crear un interfaz, dentro de persistencia->validator, alli una clase
	 *  ConstraintValidator<Phone, String>
	 * */
	@Phone
	private String telefono;

	

	public Profesor() {
		super();
		this.codigo = CODIGO_NULO;
		this.nSS = 0;
		this.email = "";
		this.direccion = "";
		this.apellidos="";
		this.nombre="";
		this.dni="";
		this.fNacimiento = new Date();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getfNacimiento() {
		return fNacimiento;
	}

	public void setfNacimiento(Date fNacimiento) {
		this.fNacimiento = fNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public long getnSS() {
		return nSS;
	}

	public void setnSS(long nSS) {
		this.nSS = nSS;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	

}
