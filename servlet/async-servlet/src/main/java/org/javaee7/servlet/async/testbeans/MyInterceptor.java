package org.javaee7.servlet.async.testbeans;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.AsyncEvent;


@Interceptor
@TestInterceptor
public class MyInterceptor {

	public static final String MESSAGE = "INTERCEPTOR";
	
    public MyInterceptor() {
    }

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ic) throws Exception {
        
    	((AsyncEvent)ic.getParameters()[0]).getAsyncContext().getResponse().getWriter().print(MESSAGE);
        return ic.proceed();
    }

}
