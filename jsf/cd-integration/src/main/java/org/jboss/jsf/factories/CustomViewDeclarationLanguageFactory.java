package org.jboss.jsf.factories;

import javax.faces.view.ViewDeclarationLanguage;
import javax.faces.view.ViewDeclarationLanguageFactory;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;

public class CustomViewDeclarationLanguageFactory extends ViewDeclarationLanguageFactory {
    
    private ViewDeclarationLanguageFactory delegate;
    
    @Inject
    BackingBean bean;
    
    public CustomViewDeclarationLanguageFactory(ViewDeclarationLanguageFactory factory){
        delegate = factory;
    }

    @Override
    public ViewDeclarationLanguage getViewDeclarationLanguage(String viewId) {

    	bean.addInjection(this.getClass().getName());
    	return delegate.getViewDeclarationLanguage(viewId);
    }

}
