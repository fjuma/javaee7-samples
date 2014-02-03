package org.jboss.jsf.factories;

import java.util.Collection;
import java.util.Set;

import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitContextFactory;
import javax.faces.component.visit.VisitHint;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;

public class CustomVisitContextFactory extends VisitContextFactory {
    
    private VisitContextFactory delegate;
    
    @Inject
    BackingBean bean;
    
    public CustomVisitContextFactory(VisitContextFactory factory){
        delegate = factory;
    }

    @Override
    public VisitContext getVisitContext(FacesContext context, Collection<String> ids, Set<VisitHint> hints) {
    	bean.addInjection(this.getClass().getName());
        return delegate.getVisitContext(context, ids, hints);
    }

}
