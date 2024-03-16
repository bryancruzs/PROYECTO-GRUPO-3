package hn.limpiezahn.views.empleados;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility;

import hn.limpiezahn.controller.InteractorEmpleado;
import hn.limpiezahn.controller.InteractorImplEmpleado;
import hn.limpiezahn.controller.InteractorImplServicio;
import hn.limpiezahn.controller.InteractorSERVICIOS;
import hn.limpiezahn.data.Empleados;
import hn.limpiezahn.data.ORDEN;
import hn.limpiezahn.data.SERVICIOS;
import hn.limpiezahn.data.SamplePerson;
import hn.limpiezahn.views.MainLayout;
import hn.limpiezahn.views.ordenedeservicio.ORDENEDESERVICIOView;
import hn.limpiezahn.views.tiposdeservicios.ViewModelSERVICIOS;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

@PageTitle("EMPLEADOS")
@Route(value = "grid-with-filters", layout = MainLayout.class)
@Uses(Icon.class)
public class EMPLEADOSView  extends Div implements BeforeEnterObserver,ViewModelEmpleados   {

  private static final String SAMPLEPERSON_EDIT_ROUTE_TEMPLATE = null;
private final String SAMPLEPERSON_ID = "samplePersonID";
  /*  private final String SAMPLEPERSON_EDIT_ROUTE_TEMPLATE = "master-detail2/%s/edit";*/
    private Grid<Empleados> grid;
    private Filters filters;
    
    private List<Empleados> elementos;
    private InteractorEmpleado controlador;
    private Empleados EmpleadoSeleccionado;
    public EMPLEADOSView() {
    	
    	setSizeFull();
        addClassNames("e-mpleados-view");

        filters = new Filters(() -> refreshGrid());
        VerticalLayout layout = new VerticalLayout(createMobileFilters(), filters, createGrid());
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);
        
    }

    private HorizontalLayout createMobileFilters() {
        // Mobile version
        HorizontalLayout mobileFilters = new HorizontalLayout();
        mobileFilters.setWidthFull();
        mobileFilters.addClassNames(LumoUtility.Padding.MEDIUM, LumoUtility.BoxSizing.BORDER,
                LumoUtility.AlignItems.CENTER);
        mobileFilters.addClassName("mobile-filters");

        Icon mobileIcon = new Icon("lumo", "plus");
        Span filtersHeading = new Span("Filters");
        mobileFilters.add(mobileIcon, filtersHeading);
        mobileFilters.setFlexGrow(1, filtersHeading);
        mobileFilters.addClickListener(e -> {
            if (filters.getClassNames().contains("visible")) {
                filters.removeClassName("visible");
                mobileIcon.getElement().setAttribute("icon", "lumo:plus");
            } else {
                filters.addClassName("visible");
                mobileIcon.getElement().setAttribute("icon", "lumo:minus");
            }
        });
        return mobileFilters;
    }
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
       /* Optional<String> codigo= event.getRouteParameters().get(SAMPLEPERSON_ID);
        if (codigo.isPresent()) {
         Empleados Empleadoobtenido=obtenerEmpleado(codigo.get());
            if (Empleadoobtenido!=null) {
               populateForm(Empleadoobtenido);
            } else {
                Notification.show(
                        String.format("La orden no exite", codigo.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(EMPLEADOSView.class);
            
        }
        }	*/
        }

/*
    
	private void populateForm(Empleados value) {
		   this.EmpleadoSeleccionado=value;
	        if(value!=null) {
	        	codigo.setValue(value.getCodigo());;
	        	nombreCliente.setValue(value.getNombreCliente());
	            direccion.setValue(value.getDireccion());
	          servicioLimpieza.setValue(value.getServicioLimpieza()) ;
	          fechaSolicitud.setValue(value.getFechaSolicitud());
	            fechaServicio.setValue(value.getNumeroOrden());
	        	precio.setValue(value.getPrecio());
	        }
	}*/
	
	public static class Filters extends Div implements Specification<Empleados> {

        private final TextField codigo = new TextField("Codigo") ;
        private final TextField identidad = new TextField("Identidad");
        private final TextField nombre = new TextField("Nombre");
        private final TextField ocupacion = new TextField("Oupacion");
        private final NumberField sueldo = new NumberField("Sueldo");
        
        public Filters(Runnable onSearch) {
        	codigo.setId("txt_codigo");
        	identidad.setId("txt_identidad");
        	nombre.setId("txt_nombre");
        	ocupacion.setId("txt_ocupacion");
        	sueldo.setId("txt_sueldo");
            
            
            setWidthFull();
            addClassName("filter-layout");
            addClassNames(LumoUtility.Padding.Horizontal.LARGE, LumoUtility.Padding.Vertical.MEDIUM,
                    LumoUtility.BoxSizing.BORDER);        

            // Action buttons
            Button resetBtn = new Button("Cancelar");
            resetBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            resetBtn.addClickListener(e -> {
                codigo.clear();
                identidad.clear();
                nombre.clear();
                ocupacion.clear();
                sueldo.clear();
                onSearch.run();
            });
            Button searchBtn = new Button("Guardad");
            searchBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            searchBtn.addClickListener(e -> onSearch.run());
            Button primaryButton = new Button("Elminar");
            primaryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            
            Div actions = new Div(resetBtn, searchBtn,primaryButton);
            actions.addClassName(LumoUtility.Gap.SMALL);
            actions.addClassName("actions");
            

            add(codigo, identidad, nombre, ocupacion, sueldo,actions);
        }
        

        /*private Component createDateRangeFilter() {
            startDate.setPlaceholder("From");

            endDate.setPlaceholder("To");

            // For screen readers
            startDate.setAriaLabel("From date");
            endDate.setAriaLabel("To date");

            FlexLayout dateRangeComponent = new FlexLayout(startDate, new Text(" – "), endDate);
            dateRangeComponent.setAlignItems(FlexComponent.Alignment.BASELINE);
            dateRangeComponent.addClassName(LumoUtility.Gap.XSMALL);

            return dateRangeComponent;
        }

		/*@Override
		public Predicate toPredicate(Root<SamplePerson> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			// TODO Auto-generated method stub
			return null;
		}
*/
        @Override
        
        public Predicate toPredicate(Root<Empleados> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();

            if (!codigo.isEmpty()) {
                String lowerCaseFilter = codigo.getValue().toLowerCase();
                Predicate firstNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),
                        lowerCaseFilter + "%");
                Predicate lastNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
                        lowerCaseFilter + "%");
                predicates.add(criteriaBuilder.or(firstNameMatch, lastNameMatch));
            }
            if (!identidad.isEmpty()) {
                String lowerCaseFilter = identidad.getValue().toLowerCase();
                Predicate firstNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),
                        lowerCaseFilter + "%");
                Predicate lastNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
                        lowerCaseFilter + "%");
                predicates.add(criteriaBuilder.or(firstNameMatch, lastNameMatch));
            }
            if (!nombre.isEmpty()) {
                String lowerCaseFilter = nombre.getValue().toLowerCase();
                Predicate firstNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),
                        lowerCaseFilter + "%");
                Predicate lastNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
                        lowerCaseFilter + "%");
                predicates.add(criteriaBuilder.or(firstNameMatch, lastNameMatch));
          }
            
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        }

        private String ignoreCharacters(String characters, String in) {
            String result = in;
            for (int i = 0; i < characters.length(); i++) {
                result = result.replace("" + characters.charAt(i), "");
            }
            return result;
        }


    }

    private Component createGrid() {
    	controlador = new InteractorImplEmpleado(this);
        elementos=new ArrayList<>();
        grid = new Grid<>(Empleados.class, false);
        grid.addColumn("codigo").setAutoWidth(true);
        grid.addColumn("identidad").setAutoWidth(true);
        grid.addColumn("nombre").setAutoWidth(true);
        grid.addColumn("ocupacion").setAutoWidth(true);
        grid.addColumn("sueldo").setAutoWidth(true);
        

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        controlador.consultarEmpleado();
        return grid;
        
       /* grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(SAMPLEPERSON_EDIT_ROUTE_TEMPLATE, event.getValue().getCodigo()));
            } else {
                clearForm();
                UI.getCurrent().navigate(EMPLEADOSView.class);
            }
     

        });*/
        
    }
 
    private void clearForm() {
		// TODO Auto-generated method stub
		
	}

	private Empleados obtenerEmpleado(String codigo) {
    	Empleados encontrado=null;
    	for(Empleados emp:elementos) {
			if(emp.getCodigo().equals(codigo)) {
				encontrado=emp;
				break;
			}
		}
		return encontrado;
	}

	private void refreshGrid() {
        grid.getDataProvider().refreshAll();
    }


	
	@Override
	public void mostrarMensajeError(String mensaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarEmpleadoEnGrid(List<Empleados> items) {
		// TODO Auto-generated method stub
		Collection<Empleados>itemsCollection=items;
		grid.setItems(itemsCollection);
		this.elementos=items;

	}
	

}

