package hn.limpiezahn.model;

import hn.limpiezahn.data.ORDENResponse;
import hn.limpiezahn.data.TIPO_DE_EmpleadoResponse;
import hn.limpiezahn.data.TIPO_DE_ServicioResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface DatabaseRepository {
	@Headers({
		"Accept: aplication/json",
		"User-Agent: Retrofit-Sample-App"
	}) 
	@GET("/pls/apex/pav2_201920010328/sl/TIPO_DE_ORDEN")
	  Call<TIPO_DE_ServicioResponse> consultarServicio();
	  @Headers({
			"Accept: aplication/json",
			"User-Agent: Retrofit-Sample-App"
		})
	  @GET("/pls/apex/pav2_201920010328/sl/EMPLEADOS")
	  Call<TIPO_DE_EmpleadoResponse> consultarEmpleado();
		@Headers({
			"Accept: aplication/json",
			"User-Agent: Retrofit-Sample-App"
		}) 
		@GET("/pls/apex/pav2_201920010328/sl/ORDENES")
		  Call<ORDENResponse> consultarORDEN();
	  }


//https://apex.oracle.com/pls/apex/pav2_201920010328/sl/TIPO_DE_ORDEN
//https://apex.oracle.com/pls/apex/pav2_201920010328/sl/EMPLEADOS
///https://apex.oracle.com/pls/apex/pav2_201920010328/sl/ORDENES