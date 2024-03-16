package hn.limpiezahn.controller;

import hn.limpiezahn.data.TIPO_DE_ServicioResponse;
import hn.limpiezahn.model.DatabaseRepositoryImpl;
import hn.limpiezahn.views.tiposdeservicios.ViewModelSERVICIOS;

public class InteractorImplServicio implements InteractorSERVICIOS{
private  DatabaseRepositoryImpl modelo;
private ViewModelSERVICIOS Vista;


public InteractorImplServicio(ViewModelSERVICIOS view) {
	super();
	this.Vista=view;
	this.modelo=DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 300000L);
	
}


@Override
public void consultarSERVICIOS() {
	try {
		TIPO_DE_ServicioResponse respuesta=this.modelo.consultarServicios();
		if(respuesta== null || respuesta.getCount()==0 || respuesta.getItems()==null) {
			this.Vista.mostrarMensajeError("No hay servicios a mostras");
	}else {
		this.Vista.mostrarSERVICIOSEnGrid(respuesta.getItems());
	}
	}catch(Exception error) {
		error.printStackTrace();
	}
	
}	
		
}