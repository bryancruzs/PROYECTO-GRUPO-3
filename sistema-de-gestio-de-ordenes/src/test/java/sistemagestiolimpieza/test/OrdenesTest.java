package sistemagestiolimpieza.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

public class OrdenesTest {

	@Test
//tes para la pantalla de ordenes
	public void testGuardarOrdenes() throws InterruptedException {
		
		/*// Inicializa el WebDriver para Chrome*/
		
		WebDriver driver = new ChromeDriver();
		try {
			
		
		// Abre la página web de inicio de sesión
		driver.get("http://localhost:8080/master-detail2");
		new WebDriverWait(driver,ofSeconds(30),ofSeconds(1)).until(titleIs("ORDENE DE SERVICIO"));
		//espera 3 segundos
		Thread.sleep(30000);
		// Localiza el campo de entrada de nombre de usuario
		WebElement cNumeroOrden = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_codigo']/input"));
		WebElement cNombreCliente = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_nombreCliente']/input"));
		WebElement cDireccion = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_direccion']/input"));
		WebElement cServicioLimpieza = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_servicioLimpieza']/input"));
		WebElement cFechaSolicitud = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_fechaSolicitud ']/input"));
		WebElement cFechaServicio= driver.findElement(By.xpath("//vaadin-text-field[@id='txt_fechaServicio']/input"));
		WebElement cPrecio= driver.findElement(By.xpath("//vaadin-number-field[@id='txt_precio']/input"));
		
		WebElement cGuardar= driver.findElement(By.xpath("//xpath=//vaadin-button[@id='btn_guardar']"));
		WebElement cCancelar= driver.findElement(By.xpath("//xpath=//vaadin-button[@id='btn_cancelar']"));
		
	 
		
		
		// Ingresa el nombre de usuario
		cNumeroOrden.sendKeys("10");
		cNombreCliente.sendKeys("bryan");
		cDireccion.sendKeys("col.san carlos");
		cServicioLimpieza.sendKeys("casa  de 1 cuarto");
		cFechaSolicitud.sendKeys("19/03/2024");
		cFechaServicio.sendKeys("20/03/2024");
		cPrecio.sendKeys("1500");
		
		//espera 3 segundo
		Thread.sleep(30000);
		
		/*cGuardar.click();*/
		///3 segundo despues de guardar
		Thread.sleep(2000);
}finally {
		driver.close()	;
		//cierra el navegador
		}
	}
	@Test
	//tes de pantalla de tipo de empleados
	public void testGuardarTipoServicio() throws InterruptedException {
		assertTrue(1==1);
		/*// Inicializa el WebDriver para Chrome*/
		
		WebDriver driver = new ChromeDriver();
		try {
			
		
		// Abre la página web de inicio de sesión
		driver.get("http://localhost:8080/master-detail");
		new WebDriverWait(driver,ofSeconds(30),ofSeconds(1)).until(titleIs("TIPOS DE SERVICIOS"));
		//espera 3 segundos
		Thread.sleep(30000);
		// Localiza el campo de entrada de nombre de usuario
		WebElement cCodigo= driver.findElement(By.xpath("//vaadin-text-field[@id='txt_codigo']/input"));
		WebElement cTiposervicio= driver.findElement(By.xpath("//vaadin-text-field[@id='txt_tiposervicio']/input"));
		WebElement cDireccion = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_direccion']/input"));
		WebElement CPrecio= driver.findElement(By.xpath("//vaadin-number-field[@id='txt_precio']/input"));
		
		WebElement cGuardar= driver.findElement(By.xpath("//xpath=//vaadin-button[@id='btn_guardar']"));
		WebElement cCancelar= driver.findElement(By.xpath("//xpath=//vaadin-button[@id='btn_cancelar']"));
		
			
		// Ingresa el nombre de usuario
		    cCodigo.sendKeys("10");
		    cTiposervicio.sendKeys("bryan");
		cDireccion.sendKeys("FESITRAN");
		CPrecio.sendKeys("1500");
		
		
		//espera 3 segundo
		Thread.sleep(30000);
		
		cGuardar.click();
		///3 segundo despues de guardar
		Thread.sleep(2000);
}finally {
		driver.close()	;
		//cierra el navegador
		}
	}
	@Test
	//tes de pantalla de empleados
	public void testGuardarEmpledos() throws InterruptedException {
		
		/*// Inicializa el WebDriver para Chrome*/
		
		WebDriver driver = new ChromeDriver();
		try {
			
		
		// Abre la página web de inicio de sesión
		driver.get("http://localhost:8080/grid-with-filters");
		new WebDriverWait(driver,ofSeconds(30),ofSeconds(1)).until(titleIs("TIPOS DE SERVICIOS"));
		//espera 3 segundos
		Thread.sleep(30000);
		// Localiza el campo de entrada de nombre de usuario

    
		WebElement cCodigo= driver.findElement(By.xpath("//vaadin-text-field[@id='txt_codigo']/input"));
		WebElement cIdentidad= driver.findElement(By.xpath("//vaadin-text-field[@id='txt_identidad']/input"));
		WebElement cNombre= driver.findElement(By.xpath("//vaadin-text-field[@id='txt_nombre']/input"));
		
		WebElement cOcupacion = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_ocupacion']/input"));
		WebElement cSueldo= driver.findElement(By.xpath("//vaadin-number-field[@id='txt_sueldo']/input"));
		
		WebElement cGuardar= driver.findElement(By.xpath("//xpath=//vaadin-button[@id='btn_guardar']"));
		WebElement cCancelar= driver.findElement(By.xpath("//xpath=//vaadin-button[@id='btn_cancelar']"));
		
			
		// Ingresa el nombre de usuario
		    cCodigo.sendKeys("10");
		    cIdentidad.sendKeys("0501200201089");
		    cNombre.sendKeys("luis chavez");
		    cOcupacion.sendKeys("trabajado de limpieza");
		    cSueldo.sendKeys("16000");
	
		//espera 3 segundo
		Thread.sleep(30000);
		
		cGuardar.click();
		///3 segundo despues de guardar
		Thread.sleep(2000);
}finally {
		driver.close()	;
		//cierra el navegador
		}
	}		
	
	
}
