package org.jboss.jsf.factories;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;

public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory delegate;

	@Inject
	BackingBean bean;

	public CustomExceptionHandlerFactory(ExceptionHandlerFactory factory) {
		delegate = factory;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		bean.addInjection(this.getClass().getName());
		return delegate.getExceptionHandler();
	}

}
