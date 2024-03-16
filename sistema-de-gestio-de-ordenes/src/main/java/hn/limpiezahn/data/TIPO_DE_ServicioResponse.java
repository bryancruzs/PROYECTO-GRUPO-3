package hn.limpiezahn.data;

import java.util.List;

public class TIPO_DE_ServicioResponse {
private List <SERVICIOS> items;
private int count;

public List<SERVICIOS> getItems() {
	return items;
}
public void setItems(List<SERVICIOS> items) {
	this.items = items;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}

}
