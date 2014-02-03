package org.jboss.jsf.testbeans;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class BackingBean {

	private Set<String> injection = new HashSet<String>();
	private Set<String> interceptors = new HashSet<String>();

	public Set<String> getInterceptors() {
		return interceptors;
	}

	public void addInterceptor(String className) {
		this.interceptors.add(className);
	}

	public Set<String> getInjection() {
		return injection;
	}

	public void addInjection(String className) {
		injection.add(className);
	}

	public String simpleAction() {

		return "/second?faces-redirect=true";
	}


}
