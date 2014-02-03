package org.jboss.jsf.factories;

import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;
import org.jboss.jsf.testbeans.MyInterceptor;

public class CustomApplicationFactory extends ApplicationFactory {

    private ApplicationFactory delegate;
    
    @Inject
    BackingBean bean;
    
    public CustomApplicationFactory(ApplicationFactory factory){
        delegate = factory;
    }
    
    @Override
    @MyInterceptor
    public Application getApplication() {
    	bean.addInjection(this.getClass().getName());
    	return delegate.getApplication();
    }

    @Override
    public void setApplication(Application application) {
        delegate.setApplication(application);
    }

}
