package hn.limpiezahn.data;

import java.util.List;

public class ORDENResponse {
private List <ORDEN> items;
private int count;

public List<ORDEN> getItems() {
	return items;
}
public void setItems(List<ORDEN> items) {
	this.items = items;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}

}
