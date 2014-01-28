package org.javaee7.servlet.event.listeners.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import org.javaee7.servlet.event.listeners.MyContextAttributeListener;
import org.javaee7.servlet.event.listeners.MyContextListener;
import org.javaee7.servlet.event.listeners.MyHttpSessionAttributeListener;
import org.javaee7.servlet.event.listeners.MyHttpSessionBindingListener;
import org.javaee7.servlet.event.listeners.MyServletRequestAttributeListener;
import org.javaee7.servlet.event.listeners.MyServletRequestListener;
import org.javaee7.servlet.event.listeners.MySessionIdListener;
import org.javaee7.servlet.event.listeners.MySessionListener;
import org.javaee7.servlet.event.listeners.TestServlet;
import org.javaee7.testbeans.RequestScopedBean;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@RunWith(Arquillian.class)
@RunAsClient
public class ServletEventListenersCdiTest {

	private final static String WEBINF = "src/main/webapp/WEB-INF";
	private final String INJECTION_MESS = "INJECTION TO ";
	private final String INTERCEPTOR_MESS = "INTERCEPTOR for ";

	@ArquillianResource
	URL url;

	@Deployment(testable = false)
	public static Archive<?> createDeployment() {

		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(TestServlet.class.getPackage())
				.addPackage(RequestScopedBean.class.getPackage())
				.addAsWebResource(new File("src/main/webapp", "index.jsp"))
				.addAsWebInfResource((new File(WEBINF, "web.xml")))
				.addAsWebInfResource(new File(WEBINF, "beans.xml"));

	}

	private String executeServlet() throws Exception {
		WebClient webClient = new WebClient();
		HtmlPage page = webClient.getPage(url);
		HtmlPage page2 = page.getElementById("link").click();
		return page2.asText();
	}

	@Test
	public void testContextAttributeListener() throws Exception {
		assertTrue("Injection to "+MyContextAttributeListener.class.getSimpleName()+" didn't happen!", executeServlet().contains(INJECTION_MESS+MyContextAttributeListener.class.getSimpleName()));
		assertTrue("Interceptor for "+MyContextAttributeListener.class.getSimpleName()+" wasn't executed!", executeServlet().contains(INTERCEPTOR_MESS +MyContextAttributeListener.class.getSimpleName()));
	}
	
	@Test
	public void testContextListener() throws Exception {
		assertTrue("Injection to "+MyContextListener.class.getSimpleName()+" didn't happen!", executeServlet().contains(INJECTION_MESS+MyContextListener.class.getSimpleName()));
		assertTrue("Interceptor for "+MyContextListener.class.getSimpleName()+" wasn't executed!", executeServlet().contains(INTERCEPTOR_MESS +MyContextListener.class.getSimpleName()));
	}
	
	@Test
	public void testHttpSessionAttributeListener() throws Exception {
		assertTrue("Injection to "+MyHttpSessionAttributeListener.class.getSimpleName()+" didn't happen!", executeServlet().contains(INJECTION_MESS+MyHttpSessionAttributeListener.class.getSimpleName()));
		assertTrue("Interceptor for "+MyHttpSessionAttributeListener.class.getSimpleName()+" wasn't executed!", executeServlet().contains(INTERCEPTOR_MESS +MyHttpSessionAttributeListener.class.getSimpleName()));
	}
	
	@Test
	public void testHttpSessionBindingListener() throws Exception {
		assertTrue("Injection to "+MyHttpSessionBindingListener.class.getSimpleName()+" didn't happen!", executeServlet().contains(INJECTION_MESS+MyHttpSessionBindingListener.class.getSimpleName()));
		assertTrue("Interceptor for "+MyHttpSessionBindingListener.class.getSimpleName()+" wasn't executed!", executeServlet().contains(INTERCEPTOR_MESS +MyHttpSessionBindingListener.class.getSimpleName()));
	}
	
	@Test
	public void testServletRequestAttributeListener() throws Exception {
		assertTrue("Injection to "+MyServletRequestAttributeListener.class.getSimpleName()+" didn't happen!", executeServlet().contains(INJECTION_MESS+MyServletRequestAttributeListener.class.getSimpleName()));
		assertTrue("Interceptor for "+MyServletRequestAttributeListener.class.getSimpleName()+" wasn't executed!", executeServlet().contains(INTERCEPTOR_MESS +MyServletRequestAttributeListener.class.getSimpleName()));
	}
	
	@Test
	public void testServletRequestListener() throws Exception {
		assertTrue("Injection to "+MyServletRequestListener.class.getSimpleName()+" didn't happen!", executeServlet().contains(INJECTION_MESS+MyServletRequestListener.class.getSimpleName()));
		assertTrue("Interceptor for "+MyServletRequestListener.class.getSimpleName()+" wasn't executed!", executeServlet().contains(INTERCEPTOR_MESS +MyServletRequestListener.class.getSimpleName()));
	}
	
	@Test
	public void testSessionIdListener() throws Exception {
		assertTrue("Injection to "+MySessionIdListener.class.getSimpleName()+" didn't happen!", executeServlet().contains(INJECTION_MESS+MySessionIdListener.class.getSimpleName()));
		assertTrue("Interceptor for "+MySessionIdListener.class.getSimpleName()+" wasn't executed!", executeServlet().contains(INTERCEPTOR_MESS +MySessionIdListener.class.getSimpleName()));
	}
	
	@Test
	public void testSessionListener() throws Exception {
		assertTrue("Injection to "+MySessionListener.class.getSimpleName()+" didn't happen!", executeServlet().contains(INJECTION_MESS+MySessionListener.class.getSimpleName()));
		assertTrue("Interceptor for "+MySessionListener.class.getSimpleName()+" wasn't executed!", executeServlet().contains(INTERCEPTOR_MESS +MySessionListener.class.getSimpleName()));
	}
	
	

}
