package org.jboss.jsf.testbeans;

import javax.faces.application.StateManager;
import javax.faces.application.StateManagerWrapper;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class CustomStateManager extends StateManagerWrapper {

    @Inject
    BackingBean bean;
    
    private StateManager delegate;

    public CustomStateManager(StateManager oldStateManager) {
        this.delegate = oldStateManager;
    }

    public StateManager getWrapped() {
        return delegate;
    }
    
    @Override
    public String getViewState(FacesContext context) {
    	bean.addInjection(this.getClass().getName());
        return delegate.getViewState(context);

    }
    
    @Override
    @MyInterceptor
    public UIViewRoot restoreView(FacesContext context, String viewId,
                                  String renderKitId) {
    	bean.addInjection(this.getClass().getName());
        return delegate.restoreView(context, viewId, renderKitId);

    }

}
