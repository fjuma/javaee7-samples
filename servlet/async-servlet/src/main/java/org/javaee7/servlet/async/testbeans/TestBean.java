package org.javaee7.servlet.async.testbeans;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class TestBean {

	public TestBean() {

	}

	private String message = "Hello";

	public String getMessage() {
		return message;
	}

}
