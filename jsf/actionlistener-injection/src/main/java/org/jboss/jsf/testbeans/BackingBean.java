package org.jboss.jsf.testbeans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class BackingBean implements Serializable{

	private int injections = 0;
	private int interceptors = 0;

	public int getInterceptors() {
		return interceptors;
	}

	public void addInterceptor() {
		this.interceptors = interceptors + 1;
	}

	public int getInjections() {
		return injections;
	}

	public void addInjection() {
		this.injections = injections + 1;
	}

	public String simpleAction() {
		return "/second?faces-redirect=true";
	}

}
