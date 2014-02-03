package org.jboss.jsf.listeners;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;
import org.jboss.jsf.testbeans.MyInterceptor;

public class MyActionListener2 implements ActionListener {

	@Inject
	BackingBean bean;
	
	public MyActionListener2(){
		
	}

	@Override
	@MyInterceptor
	public void processAction(ActionEvent event)
			throws AbortProcessingException {

		bean.addInjection();
		try {
			String path = FacesContext.getCurrentInstance()
					.getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(path.concat("/second.jsf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
