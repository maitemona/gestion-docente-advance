package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.ipartek.formacion.dbms.persistence.validator.Dni;
import com.ipartek.formacion.dbms.persistence.validator.Phone;


@Dni.List({ @Dni(profesorcodigo = "codigo", profesorKey = "dni", message = "El DNI ya existe en la base de datos")})

public class Profesor implements Comparable<Profesor>, Serializable{
	

	private static final long serialVersionUID = -6698866485450376235L;
	public static final int CODIGO_NULO = -1;
	private String nSS;
	private int codigo;
	/*validacion standar de java, las anotaciones son de java las implementacion es de hibernate*/
    @NotNull(message = "NotEmpty.dni")
    @NotBlank(message = "NotBlank.dni")
	@Pattern(regexp = "[0-9]{8}[a-z-A-Z]",  message = "Pattern.dni")
	//@Dni(message = "Dni.unico")
    private String dni;
    @Size(min=3,max=50, message = "Size.nombre")
	private String nombre;
    @Size(min=7,max=150,message = "Size.apellidos")
	private String apellidos;
    
    /*le pasamos el pattern de fecha y le decimo q se*/
    @NotNull(message="No debe ser nulo")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past(message = "Past.fNacimiento")
	private Date fNacimiento;
    @NotNull(message = "NotEmpty.email")
	@NotBlank(message = "NotBlank.email")
	@Email(message = "Email.email")
	private String email;
	private String direccion;
	private String poblacion;
	private int codigopostal;
	/*creamos una anotacion dentro de la estructura java:@Phone
	 * hay q crear un interfaz, dentro de persistencia->validator, alli una clase
	 *  ConstraintValidator<Phone, String>
	 * */
	@NotNull(message = "NotEmpty.telefono")
	@NotBlank(message = "NotBlank.telefono")
	@Phone(message = "Phone.telefono")
	private String telefono;
	private boolean activo;
	

	public Profesor() {
		super();
		this.codigo = CODIGO_NULO;
		this.nSS ="";
		this.email = "";
		this.direccion = "";
		this.apellidos="";
		this.nombre="";
		this.dni="";
		this.direccion="";
		this.poblacion="";
		this.codigopostal=48;
		this.fNacimiento = new Date();
		this.activo=true;
	}

	
	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
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

	public String getnSS() {
		return nSS;
	}

	public void setnSS(String nSS) {
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
	
	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public int getCodigopostal() {
		return codigopostal;
	}

	public void setCodigopostal(int codigopostal) {
		this.codigopostal = codigopostal;
	}

	@Override
	public String toString() {
		return "Profesor [nSS=" + nSS + ", codigo=" + codigo + ", dni=" + dni + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", fNacimiento=" + fNacimiento + ", email=" + email + ", direccion=" + direccion
				+ ", telefono=" + telefono + "]";
	}

	@Override
	public int compareTo(Profesor o) {
		return this.getApellidos().compareToIgnoreCase(o.getApellidos());
		
	}
	/**
	 * Para evaluar si los objetos son iguales
	 */

	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj instanceof Profesor) {
			Profesor profe = (Profesor) obj;
			if (this.codigo == profe.getCodigo()) {
				iguales = true;
			}
		}
		return iguales;
	}
}
