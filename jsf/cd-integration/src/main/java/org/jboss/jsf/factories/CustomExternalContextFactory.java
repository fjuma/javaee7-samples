package org.jboss.jsf.factories;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.ExternalContextFactory;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;


public class CustomExternalContextFactory extends ExternalContextFactory {

    private ExternalContextFactory delegate;
    
    @Inject
    BackingBean bean;
    
    public CustomExternalContextFactory(ExternalContextFactory factory){
        delegate = factory;
    }
    
    @Override
    public ExternalContext getExternalContext(Object context, Object request, Object response) throws FacesException {
    	bean.addInjection(this.getClass().getName());
        return delegate.getExternalContext(context, request, response);
    }

}
