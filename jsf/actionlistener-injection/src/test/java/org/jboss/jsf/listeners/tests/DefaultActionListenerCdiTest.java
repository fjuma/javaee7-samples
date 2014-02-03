package org.jboss.jsf.listeners.tests;


import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.jsf.listeners.ActionListenerBean;
import org.jboss.jsf.testbeans.BackingBean;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@RunWith(Arquillian.class)
@RunAsClient
public class DefaultActionListenerCdiTest {

	private final static String WEBINF = "src/main/webapp/WEB-INF";

	@ArquillianResource
	URL url;

	@Deployment(testable = false)
	public static Archive<?> createDeployment() {

		return ShrinkWrap.create(WebArchive.class, "test2.war")
				.addPackage(ActionListenerBean.class.getPackage())
				.addPackage(BackingBean.class.getPackage())
				.addAsWebResource(new File("src/main/webapp", "index.html"))
				.addAsWebResource(new File("src/main/webapp", "home.xhtml"))
				.addAsWebResource(new File("src/main/webapp", "second.xhtml"))
				.addAsWebInfResource("faces-config.xml", "faces-config.xml")
				.addAsWebInfResource(new File(WEBINF, "web.xml"))
				.addAsWebInfResource(new File(WEBINF, "beans.xml"));

	}

	private String clickOnAction(String id) throws Exception {
		WebClient webClient = new WebClient();
		HtmlPage page = webClient.getPage(url);
		HtmlPage page2 = page.getElementById(id).click();
		return page2.asText();
	}

	@Test
	public void testDefaultActionListener() throws Exception {
		assertTrue("Injection to action listener didn't happen!", clickOnAction("form:link3").contains("Number of injections 1"));
		assertTrue("Interceptor for action listener wasn't executed!", clickOnAction("form:link3").contains("Number of interceptors 1"));
	}
	
	

}
