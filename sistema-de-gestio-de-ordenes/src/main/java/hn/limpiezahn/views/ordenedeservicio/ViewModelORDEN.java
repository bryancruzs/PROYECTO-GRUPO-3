package hn.limpiezahn.views.ordenedeservicio;

import java.util.List;

import hn.limpiezahn.data.Empleados;
import hn.limpiezahn.data.ORDEN;

public interface ViewModelORDEN {
	
	void mostrarMensajeError(String mensaje);
	void mostrarORDENEnGrid(List<ORDEN> items);
}
