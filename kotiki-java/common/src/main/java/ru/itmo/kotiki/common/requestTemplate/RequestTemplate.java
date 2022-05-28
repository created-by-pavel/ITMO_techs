package ru.itmo.kotiki.common.requestTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestTemplate implements Serializable {
    private final ArrayList<Object> parameters;
    private String route;

    public RequestTemplate() {
        parameters = new ArrayList<>();
    }

    public RequestTemplate(Object... objects) {
        parameters = new ArrayList<>();
        Collections.addAll(parameters, objects);
    }

    public void addParameter(Object object) {
        parameters.add(object);
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
