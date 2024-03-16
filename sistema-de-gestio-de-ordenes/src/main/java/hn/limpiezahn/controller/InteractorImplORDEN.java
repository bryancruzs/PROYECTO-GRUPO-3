package hn.limpiezahn.controller;

import hn.limpiezahn.data.ORDENResponse;
import hn.limpiezahn.data.TIPO_DE_EmpleadoResponse;
import hn.limpiezahn.data.TIPO_DE_ServicioResponse;
import hn.limpiezahn.model.DatabaseRepositoryImpl;
import hn.limpiezahn.views.empleados.ViewModelEmpleados;
import hn.limpiezahn.views.ordenedeservicio.ViewModelORDEN;
import hn.limpiezahn.views.tiposdeservicios.ViewModelSERVICIOS;

public class InteractorImplORDEN implements InteractorORDEN{
private  DatabaseRepositoryImpl modelo;
private ViewModelORDEN Vista;


public InteractorImplORDEN(ViewModelORDEN view) {
	super();
	this.Vista=view;
	this.modelo=DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 300000L);
	
}


@Override
public void consultarORDEN() {
	try {
		ORDENResponse respuesta=this.modelo.consultarORDEN();
		if(respuesta== null || respuesta.getCount()==0 || respuesta.getItems()==null) {
			this.Vista.mostrarMensajeError("No hay servicios a mostras");
	}else {
		this.Vista.mostrarORDENEnGrid(respuesta.getItems());
	}
	}catch(Exception error) {
		error.printStackTrace();
	}
	
}	
		
}