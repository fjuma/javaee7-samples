package org.javaee7.session.activation.listener.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import org.javaee7.session.activation.beans.TestBean;
import org.javaee7.session.activation.listener.TestServlet;
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
public class SessionActivationListenerTest {

	private static final String WEBINF = "src/main/webapp/WEB-INF";

	@ArquillianResource
	URL url;

	@Deployment(testable = false)
	public static Archive<?> createDeployment() {

		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(TestServlet.class.getPackage())
				.addPackage(TestBean.class.getPackage())
				.addAsWebResource(new File("src/main/webapp", "index.jsp"))
				.addAsWebInfResource((new File(WEBINF, "web.xml")))
				.addAsWebInfResource(new File(WEBINF, "beans.xml"));

	}

	private HtmlPage getServletReponsePage() throws Exception {
		WebClient webClient = new WebClient();
		HtmlPage page = webClient.getPage(url);
		HtmlPage page2 = page.getElementById("link").click();
		return page2;
	}

	@Test
	public void testPassivationCalled() throws Exception {
		// refresh page to initiate passivation event 
		String body = ((HtmlPage)getServletReponsePage().refresh()).asText();
		assertTrue("Session context not active during passivation event!", body.contains("ACTIVE BEFORE PASSIVATION true"));
	}
	
	@Test
	public void testActivationCalled() throws Exception {
		// refresh page to initiate activation event 
		String body = ((HtmlPage)getServletReponsePage().refresh()).asText();
		assertTrue("Session context not active during activation event!", body.contains("ACTIVE AFTER ACTIVATION true"));

	}

}
