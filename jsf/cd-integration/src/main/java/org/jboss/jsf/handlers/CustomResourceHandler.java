package org.jboss.jsf.handlers;

import javax.faces.application.ResourceHandler;
import javax.faces.application.ResourceHandlerWrapper;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;

public class CustomResourceHandler extends ResourceHandlerWrapper {
    
    
    private ResourceHandler delegate;
    
    @Inject
    BackingBean bean;
    
    public CustomResourceHandler(ResourceHandler handler){
        delegate = handler;
    }

    @Override
    public ResourceHandler getWrapped() {
    	bean.addInjection(this.getClass().getName());
    	return delegate;
    }

}
