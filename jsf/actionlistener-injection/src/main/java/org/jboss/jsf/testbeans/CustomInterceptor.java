package org.jboss.jsf.testbeans;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@MyInterceptor
public class CustomInterceptor implements Serializable{
	
	@Inject
	BackingBean bean;

    public CustomInterceptor() {
        // TODO Auto-generated constructor stub
    }

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ic) throws Exception {
    	bean.addInterceptor();
        return ic.proceed();
    }

}
