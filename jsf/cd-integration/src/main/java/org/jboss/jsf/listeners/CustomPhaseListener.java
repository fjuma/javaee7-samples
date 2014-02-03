package org.jboss.jsf.listeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;
import org.jboss.jsf.testbeans.MyInterceptor;

public class CustomPhaseListener implements PhaseListener {

    @Inject
    BackingBean bean;
    
    public CustomPhaseListener(){
        
    }
    
    public void afterPhase(PhaseEvent event) {
    	bean.addInjection(this.getClass().getName());
    }

    @MyInterceptor
    public void beforePhase(PhaseEvent event) {
        
    }
    
    public PhaseId getPhaseId() {
        // TODO Auto-generated method stub
        return PhaseId.ANY_PHASE;
    }

}
