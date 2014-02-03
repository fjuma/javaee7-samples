package org.jboss.jsf.handlers;

import javax.faces.application.NavigationHandler;
import javax.faces.application.NavigationHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;

public class CustomNavigationHandler extends NavigationHandlerWrapper {

    private NavigationHandler delegate;
    
    @Inject
    BackingBean bean;
    
    public CustomNavigationHandler(NavigationHandler handler){
        delegate = handler;
    }
    
    @Override
    public void handleNavigation(FacesContext context, String fromAction, String outcome) {
    	bean.addInjection(this.getClass().getName());
    	delegate.handleNavigation(context, fromAction, outcome);
    }
    
    @Override
    public NavigationHandler getWrapped() {
    	return delegate;
    }

}
