package org.jboss.jsf.factories;

import java.util.Iterator;

import javax.faces.context.FacesContext;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;

public class CustomRenderKitFactory extends RenderKitFactory {

    
    private RenderKitFactory delegate;
    
    @Inject
    BackingBean bean;
    
    
    public CustomRenderKitFactory(RenderKitFactory factory){
        delegate = factory;
    }
    
    @Override
    public void addRenderKit(String renderKitId, RenderKit renderKit) {
        delegate.addRenderKit(renderKitId, renderKit);
    }

    @Override
    public RenderKit getRenderKit(FacesContext context, String renderKitId) {
    	bean.addInjection(this.getClass().getName());
    	return delegate.getRenderKit(context, renderKitId);
    }

    @Override
    public Iterator<String> getRenderKitIds() {
        return delegate.getRenderKitIds();
    }

}
