package org.javaee7.servlet.async;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

import org.javaee7.servlet.async.testbeans.TestBean;
import org.javaee7.servlet.async.testbeans.TestInterceptor;

public class MyAsyncListener implements AsyncListener {
	
	@Inject
	TestBean bean;

	@Override
	@TestInterceptor
	public void onComplete(AsyncEvent event) throws IOException {
		
		event.getSuppliedResponse().getWriter().println(bean.getMessage());
	}

	@Override
	public void onTimeout(AsyncEvent event) throws IOException {

	}

	@Override
	public void onError(AsyncEvent event) throws IOException {

	}

	@Override
	public void onStartAsync(AsyncEvent event) throws IOException {
	}

}
