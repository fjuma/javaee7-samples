package org.javaee7.testbeans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RequestScopedBean {

	public RequestScopedBean() {

	}

	private String message = "Hello";
	
	private List<String> injections = new ArrayList<String>();
	private List<String> interceptors = new ArrayList<String>();

	public List<String> getInterceptors() {
		return interceptors;
	}

	public List<String> getInjections() {
		return injections;
	}

	public String getMessage() {
		return message;
	}
	
	public void addInjection(String name){
		injections.add("INJECTION TO " +name.substring(0,name.indexOf("$")));
	}
	
	public void addInterceptor(String name){
		interceptors.add(name.substring(0,name.indexOf("$")));
	}
	

}
