package org.jboss.jsf.factories;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.Flash;
import javax.faces.context.FlashFactory;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.jsf.testbeans.BackingBean;

public class CustomFlashFactory extends FlashFactory {

    private FlashFactory delegate;

    @Inject
    BackingBean bean;

    public CustomFlashFactory(FlashFactory factory) {
        delegate = factory;
    }

    @Override
    public Flash getFlash(boolean create) {
    	bean.addInjection(this.getClass().getName());
        return delegate.getFlash(create);
    }

}
