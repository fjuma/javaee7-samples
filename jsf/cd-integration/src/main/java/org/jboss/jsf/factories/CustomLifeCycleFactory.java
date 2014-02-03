package org.jboss.jsf.factories;

import java.util.Iterator;

import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;


public class CustomLifeCycleFactory extends LifecycleFactory {
    
    private LifecycleFactory delegate;
    
    @Inject
    BackingBean bean;
    
    public CustomLifeCycleFactory(LifecycleFactory factory){
        delegate = factory;
    }

    @Override
    public void addLifecycle(String lifecycleId, Lifecycle lifecycle) {
    	
    	delegate.addLifecycle(lifecycleId, lifecycle);
    }

    @Override
    public Lifecycle getLifecycle(String lifecycleId) {
    	bean.addInjection(this.getClass().getName());
        return delegate.getLifecycle(lifecycleId);
    }

    @Override
    public Iterator<String> getLifecycleIds() {
        return delegate.getLifecycleIds();
    }

}
