package org.jboss.el.resolver;

import java.beans.FeatureDescriptor;
import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.inject.Inject;

import org.jboss.jsf.testbeans.BackingBean;
import org.jboss.jsf.testbeans.MyInterceptor;

public class CustomELResolver extends ELResolver {

	@Inject
	BackingBean bean;

	@Override
	@MyInterceptor
	public Object getValue(ELContext context, Object base, Object property) {
		bean.addInjection(this.getClass().getName());
		return null;
	}

	@Override
	public Class<?> getType(ELContext context, Object base, Object property) {
		return null;
	}

	@Override
	public void setValue(ELContext context, Object base, Object property,
			Object value) {

	}

	@Override
	public boolean isReadOnly(ELContext context, Object base, Object property) {
		return false;
	}

	@Override
	public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context,
			Object base) {
		return null;
	}

	@Override
	public Class<?> getCommonPropertyType(ELContext context, Object base) {
		return null;
	}

}
