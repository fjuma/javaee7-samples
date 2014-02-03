package org.jboss.jsf.factories;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;

public class CustomFacesContextFactory extends FacesContextFactory {

    @Inject
    BackingBean bean;
    
    private FacesContextFactory delegate;

    public CustomFacesContextFactory(FacesContextFactory facesContextFactory) {
      delegate = facesContextFactory;
    }

    
    @Override
    public FacesContext getFacesContext(Object context, Object request, Object response, Lifecycle lifecycle)
            throws FacesException {
    	bean.addInjection(this.getClass().getName());
    	return delegate.getFacesContext(context, request, response, lifecycle);
    }

}
