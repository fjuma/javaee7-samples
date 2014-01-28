package org.javaee7.servlet.async;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

import org.javaee7.testbeans.ApplicationScopedBean;
import org.javaee7.testbeans.RequestScopedBean;
import org.javaee7.testbeans.TestInterceptor;


public class MyAsyncListener implements AsyncListener {
	
	@Inject
	public ApplicationScopedBean bean;

	@TestInterceptor
	public void onComplete(AsyncEvent event) throws IOException {
		
		event.getSuppliedResponse().getWriter().println(bean.getMessage());
	}

	public void onTimeout(AsyncEvent event) throws IOException {

	}

	public void onError(AsyncEvent event) throws IOException {

	}

	public void onStartAsync(AsyncEvent event) throws IOException {
	}

}
