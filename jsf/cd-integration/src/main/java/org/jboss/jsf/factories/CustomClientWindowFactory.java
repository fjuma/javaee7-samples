package org.jboss.jsf.factories;

import javax.faces.context.FacesContext;
import javax.faces.lifecycle.ClientWindow;
import javax.faces.lifecycle.ClientWindowFactory;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;

public class CustomClientWindowFactory extends ClientWindowFactory {

    private ClientWindowFactory delegate;
    
    @Inject
    BackingBean bean;
    
    public CustomClientWindowFactory(ClientWindowFactory factory){
        delegate = factory;
    }
    
    @Override
    public ClientWindow getClientWindow(FacesContext context) {
    	bean.addInjection(this.getClass().getName());
        return delegate.getClientWindow(context);
    }

}
