package org.jboss.jsf.factories;

import javax.faces.view.facelets.BehaviorHandler;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.ConverterHandler;
import javax.faces.view.facelets.TagHandlerDelegate;
import javax.faces.view.facelets.TagHandlerDelegateFactory;
import javax.faces.view.facelets.ValidatorHandler;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;

public class CustomTagHandlerDelegateFactory extends TagHandlerDelegateFactory {

    private TagHandlerDelegateFactory delegate;
    
    @Inject
    BackingBean bean;
    
    public CustomTagHandlerDelegateFactory(TagHandlerDelegateFactory factory){
        delegate = factory;
    }
    @Override
    public TagHandlerDelegate createComponentHandlerDelegate(ComponentHandler owner) {
    	bean.addInjection(this.getClass().getName());
    	return delegate.createComponentHandlerDelegate(owner);
    }

    @Override
    public TagHandlerDelegate createValidatorHandlerDelegate(ValidatorHandler owner) {
        return delegate.createValidatorHandlerDelegate(owner);
    }

    @Override
    public TagHandlerDelegate createConverterHandlerDelegate(ConverterHandler owner) {
        return delegate.createConverterHandlerDelegate(owner);
    }

    @Override
    public TagHandlerDelegate createBehaviorHandlerDelegate(BehaviorHandler owner) {
        return delegate.createBehaviorHandlerDelegate(owner);
    }

}
