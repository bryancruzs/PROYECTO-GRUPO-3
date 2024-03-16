package hn.limpiezahn.data;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;

import com.vaadin.flow.component.textfield.TextField;

@Entity
public class ORDEN extends AbstractEntity {

	private String numeroorden;

	public String getNumeroOrden() {
		return numeroorden;	
	}
	public void setNumeroOrden(String numeroOrden) {
		this.numeroorden = numeroOrden;
	}
	private String nombrecliente;
    private String direccion;
  
    public String getServicioLimpieza() {
		return serviciolimpieza;
	}
	public void setServicioLimpieza(String servicioLimpieza) {
		this.serviciolimpieza = servicioLimpieza;
	}
	private String serviciolimpieza ;
    private String fechasolicitud;
    private String fechaservicio;
    private double precio;
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getFechaServicio() {
		return fechaservicio;
	}
	public void setFechaServicio(String fechaServicio) {
		this.fechaservicio = fechaServicio;
	}

	public String getNombreCliente() {
		return nombrecliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombrecliente = nombreCliente;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFechaSolicitud() {
		return fechasolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechasolicitud = fechaSolicitud;
	}

   
	
}



