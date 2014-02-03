package org.jboss.jsf.factories;

import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.faces.context.PartialViewContextFactory;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;

public class CustomPartialViewContextFactory extends PartialViewContextFactory {

    
    private PartialViewContextFactory delegate;
    
    @Inject
    BackingBean bean;
    
    public CustomPartialViewContextFactory(PartialViewContextFactory factory){
        delegate = factory;
    }
    
    @Override
    public PartialViewContext getPartialViewContext(FacesContext context) {
    	bean.addInjection(this.getClass().getName());
        return delegate.getPartialViewContext(context);
    }

}
