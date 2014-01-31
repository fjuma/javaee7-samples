package org.jboss.jsf.listeners;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;
import org.jboss.jsf.testbeans.MyInterceptor;

public class MyActionListener implements ActionListener {
	
	
	@Inject
	BackingBean bean;

	@Override
	@MyInterceptor 
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		bean.addInjection();
	}

}
