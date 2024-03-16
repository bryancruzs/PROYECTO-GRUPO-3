package hn.limpiezahn.views.empleados;

import java.util.List;

import hn.limpiezahn.data.Empleados;

public interface ViewModelEmpleados {
	
	void mostrarMensajeError(String mensaje);
	void mostrarEmpleadoEnGrid(List<Empleados> items);
}
