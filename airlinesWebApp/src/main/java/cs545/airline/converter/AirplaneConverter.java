package cs545.airline.converter;

import cs545.airline.model.Airplane;
import cs545.airline.service.AirplaneService;

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
public class AirplaneConverter implements Converter{

    @Inject
    private AirplaneService airplaneService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }

        try {
            Airplane airplane = new Airplane();
            airplane.setId(Long.parseLong(s));
            return airplaneService.find(airplane);
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(s + " is not a valid Airplane ID"), e);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return "";
        }

        if (o instanceof Airplane) {
            return String.valueOf(((Airplane) o).getId());
        } else {
            throw new ConverterException(new FacesMessage(o + " is not a valid Airplane"));
        }
    }
}
