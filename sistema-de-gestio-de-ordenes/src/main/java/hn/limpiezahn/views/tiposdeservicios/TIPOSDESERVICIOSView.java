package hn.limpiezahn.views.tiposdeservicios;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import hn.limpiezahn.controller.InteractorImplServicio;
import hn.limpiezahn.controller.InteractorSERVICIOS;
import hn.limpiezahn.data.SERVICIOS;
import hn.limpiezahn.views.MainLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("TIPOS DE SERVICIOS")
@Route(value = "master-detail/:sampleAddressID?/:action?(edit)", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class TIPOSDESERVICIOSView extends Div implements BeforeEnterObserver,ViewModelSERVICIOS{

  private final String EMPLOYEE_C = "Codigo";
   private final String EMPLOYEE_EDIT_ROUTE_TEMPLATE = "master-detail/%s/edit";
   //https://apex.oracle.com/pls/apex/pav2_201920010328/sdo/TIPO_DE_ORDEN
    private final Grid<SERVICIOS> grid = new Grid<>(SERVICIOS.class, false);

    private TextField codigo ;
    private TextField tiposervicio;
    private TextField descripcion;
    private  NumberField precio;

    private final Button cancel = new Button("Cancelar");
    private final Button save = new Button("Guardar");

    private SERVICIOS serivicioSeleccionado;
    private List<SERVICIOS> elementos;
    private InteractorSERVICIOS controlador;



    public TIPOSDESERVICIOSView() {
        /*this.sampleAddressService = sampleAddressService;*/
        addClassNames("t-iposdeservicios-view");
    
        controlador = new InteractorImplServicio(this);
        elementos=new ArrayList<>();
        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("codigo").setAutoWidth(true);
        grid.addColumn("tiposervicio").setAutoWidth(true);
        grid.addColumn("descripcion").setAutoWidth(true);
        grid.addColumn("precio").setAutoWidth(true);
       
    grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(EMPLOYEE_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(TIPOSDESERVICIOSView.class);
            }
        });
        // Configure Form
       controlador.consultarSERVICIOS();

        // Bind fields. This is where you'd define e.g. validation rules
    }


@Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> sampleAddressId = event.getRouteParameters().get(EMPLOYEE_C).map(Long::parseLong);
        if (sampleAddressId.isPresent()) {
         /*  Optional<SERVICIOS> sampleAddressFromBackend = sampleAddressService.get(sampleAddressId.get());
            if (sampleAddressFromBackend.isPresent()) {
                populateForm(sampleAddressFromBackend.get());
            } else {
                Notification.show(
                        String.format("The requested sampleAddress was not found, ID = %s", sampleAddressId.get()),
                        3000, Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(TIPOSDESERVICIOSView.class);
            }*/
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        codigo = new TextField("Codigo");
        codigo.setId("txt_codigo");
        tiposervicio= new TextField("Tipo de Servicios");
        tiposervicio.setId("txt_tiposervicio");
        descripcion = new TextField("Descripcion");
        descripcion.setId("txt_descripcion");
        precio = new NumberField("Precio");
        codigo.setId("txt_codigo");
        formLayout.add(codigo,tiposervicio, descripcion, precio);

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

    private void populateForm(SERVICIOS value) {
        this.serivicioSeleccionado = value;
        if(value!=null) {
            codigo.setValue(value.getCodigo()); 
            tiposervicio.setValue(value.getTiposervicio());
            descripcion.setValue(value.getDescripcion());
            /*precio.setValue(value.getPrecio());*/
        	
        }
    }


	
	@Override
	public void mostrarSERVICIOSEnGrid(List<SERVICIOS> items) {
		// TODO Auto-generated method stub
		Collection<SERVICIOS>itemsCollection=items;
		grid.setItems(itemsCollection);
		this.elementos=items;
	}
	
	@Override
	public void mostrarMensajeError(String mensaje) {
		// TODO Auto-generated method stub
		Notification.show(mensaje);
	}

	
}
