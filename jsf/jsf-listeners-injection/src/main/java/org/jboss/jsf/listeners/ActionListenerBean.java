package org.jboss.jsf.listeners;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.jsf.testbeans.BackingBean;
import org.jboss.jsf.testbeans.MyInterceptor;

@Named
@SessionScoped
public class ActionListenerBean implements Serializable {

	@Inject
	BackingBean bean;

	public ActionListenerBean() {

	}

	@MyInterceptor
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		bean.addInjection();
	}

}
