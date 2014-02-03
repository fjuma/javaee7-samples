package org.jboss.jsf.factories;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowHandler;
import javax.faces.flow.FlowHandlerFactory;
import javax.faces.flow.FlowHandlerFactoryWrapper;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.jsf.testbeans.BackingBean;

@Named
@ApplicationScoped
public class CustomFlowHandlerFactory extends FlowHandlerFactoryWrapper {

    
    @Inject
    BackingBean bean;
    
    private FlowHandlerFactory wrapped;
    
    
    public CustomFlowHandlerFactory(FlowHandlerFactory factory){
        wrapped = factory;
    }
    
    @Override
    public FlowHandlerFactory getWrapped() {
        return wrapped;
    }
    
    @Override
    public FlowHandler createFlowHandler(FacesContext context){
    	bean.addInjection(this.getClass().getName());
    	return wrapped.createFlowHandler(context);
    }

}
