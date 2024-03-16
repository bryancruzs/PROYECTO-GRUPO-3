package hn.limpiezahn.data;

import java.util.List;

public class TIPO_DE_EmpleadoResponse {
	private List <Empleados> items;
	private int count;

	public List<Empleados> getItems() {
		return items;
	}
	public void setItems(List<Empleados> items) {
		this.items = items;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	}

