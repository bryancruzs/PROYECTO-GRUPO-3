package hn.limpiezahn.views.ordenedeservicio;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import hn.limpiezahn.controller.InteractorImplORDEN;
import hn.limpiezahn.controller.InteractorImplServicio;
import hn.limpiezahn.controller.InteractorORDEN;
import hn.limpiezahn.controller.InteractorSERVICIOS;
import hn.limpiezahn.data.ORDEN;
import hn.limpiezahn.data.SERVICIOS;
import hn.limpiezahn.data.SamplePerson;
import hn.limpiezahn.views.MainLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("ORDENE DE SERVICIO")
@Route(value = "master-detail2/:samplePersonID?/:action?(edit)", layout = MainLayout.class)
@Uses(Icon.class)
public class ORDENEDESERVICIOView extends Div implements BeforeEnterObserver,ViewModelORDEN {

    private final String SAMPLEPERSON_ID = "samplePersonID";
    private final String SAMPLEPERSON_EDIT_ROUTE_TEMPLATE = "master-detail2/%s/edit";

    private final Grid<ORDEN> grid = new Grid<>(ORDEN.class, false);

    private TextField numeroOrden;
    private TextField nombreCliente;
    private TextField direccion;
    private TextField servicioLimpieza;
    private TextField fechaSolicitud;
    private TextField fechaServicio;
    private NumberField precio;

    private final Button cancel = new Button("Cancelar");
    private final Button save = new Button("Guarda");

    private ORDEN ORDEN;
    private ORDEN ORDENSeleccionado;
    private List<ORDEN> elementos;
    private InteractorORDEN controlador;
    public ORDENEDESERVICIOView( ) {
     
        addClassNames("o-rdenedeservicio-view");
        controlador = new InteractorImplORDEN(this);
        elementos=new ArrayList<>();
        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
       grid.addColumn("numeroOrden").setAutoWidth(true) ;
        grid.addColumn("nombreCliente").setAutoWidth(true);
        grid.addColumn("direccion").setAutoWidth(true);
        grid.addColumn("servicioLimpieza").setAutoWidth(true);
        grid.addColumn("fechaSolicitud").setAutoWidth(true);
        grid.addColumn("fechaServicio").setAutoWidth(true);
        grid.addColumn("precio").setAutoWidth(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        
        controlador.consultarORDEN();
        
        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(SAMPLEPERSON_EDIT_ROUTE_TEMPLATE, event.getValue().getNumeroOrden()));
            } else {
                clearForm();
                UI.getCurrent().navigate(ORDENEDESERVICIOView.class);
            }
     

        });
        
     
        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.ORDEN == null) {
                    this.ORDEN = new ORDEN();
                }
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(ORDENEDESERVICIOView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<String> numeroOrden= event.getRouteParameters().get(SAMPLEPERSON_ID);
        if (numeroOrden.isPresent()) {
         ORDEN ordenobtenido=obtenerOrden(numeroOrden.get());
            if (ordenobtenido!=null) {
                populateForm(ordenobtenido);
            } else {
                Notification.show(
                        String.format("La orden no exite", numeroOrden.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(ORDENEDESERVICIOView.class);
            
        }
        }
        }
        
        

    private ORDEN obtenerOrden(String numeroOrden) {
		ORDEN encontrado=null;
    	for(ORDEN emp:elementos) {
			if(emp.getNumeroOrden().equals(numeroOrden)) {
				encontrado=emp;
				break;
			}
		}
		return encontrado;
	}

	private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        numeroOrden = new TextField("Numero de Orden");
        numeroOrden.setId("txt_codigo");
        
        nombreCliente = new TextField("Nombre del CLiente");
        nombreCliente.setId("txt_nombreCliente");
        
        direccion = new TextField("Direccion");
        direccion.setId("txt_direccion");
        
        servicioLimpieza = new TextField("Servicio de Limpieza");
        servicioLimpieza.setId("txt_servicioLimpieza");
        
        fechaSolicitud = new TextField("Fecha de Solicitud");
        fechaSolicitud .setId("txt_fechaSolicitud ");
        
        fechaServicio= new TextField("Fecha de Servicio");
        fechaServicio.setId("txt_fechaServicio");
        
        precio = new NumberField("Precio");
        precio.setId("txt_precio");
        
        formLayout.add(numeroOrden,nombreCliente, direccion, servicioLimpieza, fechaSolicitud, fechaServicio, precio);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.setId("btn_cancelar");
        
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.setId("btn_guardar");
        
        
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(ORDEN value) {
        this.ORDENSeleccionado=value;
        if(value!=null) {
        	numeroOrden.setValue(value.getNumeroOrden());;
        	nombreCliente.setValue(value.getNombreCliente());
            direccion.setValue(value.getDireccion());
          servicioLimpieza.setValue(value.getServicioLimpieza()) ;
          fechaSolicitud.setValue(value.getFechaSolicitud());
            fechaServicio.setValue(value.getFechaServicio());
        	precio.setValue(value.getPrecio());
        }
        
        

    }

	@Override
	public void mostrarMensajeError(String mensaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarORDENEnGrid(List<hn.limpiezahn.data.ORDEN> items) {
		// TODO Auto-generated method stub
		Collection<ORDEN>itemsCollection=items;
		grid.setItems(itemsCollection);
		this.elementos=items;
	}

	
	
}
