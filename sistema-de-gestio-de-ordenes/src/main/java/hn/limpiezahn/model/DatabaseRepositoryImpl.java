package hn.limpiezahn.model;

import java.io.IOException;

import hn.limpiezahn.data.ORDENResponse;
import hn.limpiezahn.data.TIPO_DE_EmpleadoResponse;
import hn.limpiezahn.data.TIPO_DE_ServicioResponse;
import retrofit2.Call;
import retrofit2.Response;

public class DatabaseRepositoryImpl {

	private static DatabaseRepositoryImpl INSTANCE;
	private DatabaseClient client;
	//patron singleton
	private DatabaseRepositoryImpl(String url, Long timeout) {
		client= new DatabaseClient(url,timeout);
		
	}
	public static DatabaseRepositoryImpl getInstance(String url, Long timeout) {
		if(INSTANCE==null) {
			synchronized(DatabaseRepositoryImpl.class) {
				if(INSTANCE == null) {
					INSTANCE=new DatabaseRepositoryImpl(url, timeout);
				}
			}
		}
		return INSTANCE;
	}
	
// crearcion de operaciones a la base de datos
	public TIPO_DE_ServicioResponse consultarServicios() throws IOException  {
	Call<TIPO_DE_ServicioResponse> call=client.getDB().consultarServicio();
	Response<TIPO_DE_ServicioResponse> response= call.execute(); //AQUI DONDE SE LLAMA A BD
	if(response.isSuccessful()) {
		return response.body();
		
	}
	else {
		return null;
	}
	}
	public TIPO_DE_EmpleadoResponse consultarEmpleados() throws IOException  {
		Call<TIPO_DE_EmpleadoResponse> call=client.getDB().consultarEmpleado();
		Response<TIPO_DE_EmpleadoResponse> response= call.execute(); //AQUI DONDE SE LLAMA A BD
		if(response.isSuccessful()) {
			return response.body();
			
		}
		else {
			return null;
		
}
	}
	public ORDENResponse consultarORDEN() throws IOException  {
		Call<ORDENResponse> call=client.getDB().consultarORDEN();
		Response<ORDENResponse> response= call.execute(); //AQUI DONDE SE LLAMA A BD
		if(response.isSuccessful()) {
			return response.body();
			
		}
		else {
			return null;
		
}
	}
	
}
	

