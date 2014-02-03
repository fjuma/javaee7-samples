package org.jboss.jsf.listeners;

import javax.faces.application.Application;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;
import org.jboss.jsf.testbeans.MyInterceptor;

public class CustomSystemEventListener implements SystemEventListener {
    
    @Inject
    BackingBean bean;

    @MyInterceptor
    public void processEvent(SystemEvent event) throws AbortProcessingException {
    	bean.addInjection(this.getClass().getName());        

    }

    public boolean isListenerForSource(Object source) {
        return (source instanceof Application);
    }

}
