package cs545.airline.converter;

import cs545.airline.model.Airport;
import cs545.airline.service.AirportService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AirportConverter implements Converter{

    @Inject
    private AirportService airportService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }

        try {
            Airport airport = new Airport();
            airport.setId(Long.parseLong(s));
            return airportService.find(airport);
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(s + " is not a valid Airport ID"), e);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return "";
        }

        if (o instanceof Airport) {
            return String.valueOf(((Airport) o).getId());
        } else {
            throw new ConverterException(new FacesMessage(o + " is not a valid Airport"));
        }
    }
}
