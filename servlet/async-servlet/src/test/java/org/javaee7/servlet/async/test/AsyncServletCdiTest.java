package org.javaee7.servlet.async.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import javax.inject.Inject;

import org.javaee7.servlet.async.MyAsyncServlet;
import org.javaee7.servlet.async.testbeans.MyInterceptor;
import org.javaee7.servlet.async.testbeans.TestBean;
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
public class AsyncServletCdiTest {

	private static final String WEBINF = "src/main/webapp/WEB-INF";

	@Inject
	TestBean bean;

	@ArquillianResource
	URL url;

	@Deployment(testable = false)
	public static Archive<?> createDeployment() {

		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(MyAsyncServlet.class.getPackage())
				.addPackage(TestBean.class.getPackage())
				.addAsWebResource("index.jsp","index.jsp")
				.addAsWebInfResource(
						(new File(WEBINF, "web.xml")))
				.addAsWebInfResource(new File(WEBINF, "beans.xml"));

	}

	private String executeServlet() throws Exception{
		WebClient webClient = new WebClient();
		HtmlPage page = webClient.getPage(url);
		HtmlPage page2 = page.getElementById("link").click();
		return page2.asText();
	}
	

	@Test
	public void testInjectionToAsyncListener() throws Exception {
		assertTrue("Bean was not injected!", executeServlet().contains("Hello"));
	}
	
	@Test
	public void testInterceptionInAsyncListener() throws Exception {
		assertTrue("Interceptor was not executed!", executeServlet().contains(MyInterceptor.MESSAGE));
	}

}
