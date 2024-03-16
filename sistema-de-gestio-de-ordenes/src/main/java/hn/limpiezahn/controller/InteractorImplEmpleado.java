package hn.limpiezahn.controller;

import hn.limpiezahn.data.TIPO_DE_EmpleadoResponse;
import hn.limpiezahn.data.TIPO_DE_ServicioResponse;
import hn.limpiezahn.model.DatabaseRepositoryImpl;
import hn.limpiezahn.views.empleados.ViewModelEmpleados;
import hn.limpiezahn.views.tiposdeservicios.ViewModelSERVICIOS;

public class InteractorImplEmpleado implements InteractorEmpleado{
private  DatabaseRepositoryImpl modelo;
private ViewModelEmpleados Vista;


public InteractorImplEmpleado(ViewModelEmpleados view) {
	super();
	this.Vista=view;
	this.modelo=DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 300000L);
	
}


@Override
public void consultarEmpleado() {
	try {
		TIPO_DE_EmpleadoResponse respuesta=this.modelo.consultarEmpleados();
		if(respuesta== null || respuesta.getCount()==0 || respuesta.getItems()==null) {
			this.Vista.mostrarMensajeError("No hay servicios a mostras");
	}else {
		this.Vista.mostrarEmpleadoEnGrid(respuesta.getItems());
	}
	}catch(Exception error) {
		error.printStackTrace();
	}
	
}	
		
}