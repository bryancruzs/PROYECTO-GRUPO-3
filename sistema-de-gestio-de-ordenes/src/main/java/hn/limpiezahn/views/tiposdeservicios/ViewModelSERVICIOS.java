package hn.limpiezahn.views.tiposdeservicios;

import java.util.List;

import hn.limpiezahn.data.SERVICIOS;

public interface ViewModelSERVICIOS {
void mostrarSERVICIOSEnGrid(List<SERVICIOS>items);
void mostrarMensajeError(String mensaje);

}
