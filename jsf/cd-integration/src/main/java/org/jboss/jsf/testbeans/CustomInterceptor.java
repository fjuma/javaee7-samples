package org.jboss.jsf.testbeans;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@MyInterceptor
public class CustomInterceptor {

	@Inject
	BackingBean bean;
	
	
    public CustomInterceptor() {

    }

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ic) throws Exception {
    	
    	bean.addInterceptor(ic.getTarget().getClass().getName());
        return ic.proceed();
    }

}
