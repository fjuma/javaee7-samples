package org.javaee7.testbeans;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.AsyncEvent;

@Interceptor
@TestInterceptor
public class MyInterceptor {

	public static final String MESSAGE = "INTERCEPTOR ";
	
	@Inject
	ApplicationScopedBean bean;

	public MyInterceptor() {
	}

	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {

		if (ic.getParameters()[0] instanceof AsyncEvent) {
			((AsyncEvent) ic.getParameters()[0]).getAsyncContext()
					.getResponse().getWriter().print(MESSAGE);

		} else {
			bean.addInterceptor(MESSAGE+"for "+ic.getTarget().getClass().getSimpleName());
			/*String className = ic.getTarget().getClass().getSimpleName();
			Field atr = ic.getTarget().getClass().getFields()[0];

			if (atr.get(ic.getTarget()) instanceof RequestScopedBean) {
				((RequestScopedBean) atr.get(ic.getTarget()))
						.addInterceptor(MESSAGE
								+ "for "
								+ className.substring(0, className.indexOf("$")));

			} else if (atr.get(ic.getTarget()) instanceof ApplicationScopedBean) {
				((ApplicationScopedBean) atr.get(ic.getTarget()))
						.addInterceptor(MESSAGE
								+ "for "
								+ className.substring(0, className.indexOf("$")));
			}*/
		}
		return ic.proceed();
	}

}
