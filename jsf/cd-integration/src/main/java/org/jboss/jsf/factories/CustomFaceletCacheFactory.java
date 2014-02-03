package org.jboss.jsf.factories;

import javax.faces.view.facelets.FaceletCache;
import javax.faces.view.facelets.FaceletCacheFactory;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;

public class CustomFaceletCacheFactory extends FaceletCacheFactory {
    
    private FaceletCacheFactory delegate;
    
    @Inject
    BackingBean bean;
    
    public CustomFaceletCacheFactory(FaceletCacheFactory factory){
        delegate = factory;
    }

    @Override
    public FaceletCache getFaceletCache() {
    	bean.addInjection(this.getClass().getName());
        return delegate.getFaceletCache();
    }

}
