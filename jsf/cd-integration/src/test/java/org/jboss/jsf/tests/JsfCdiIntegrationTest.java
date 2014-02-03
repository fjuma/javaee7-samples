package org.jboss.jsf.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.el.resolver.CustomELResolver;
import org.jboss.jsf.factories.CustomApplicationFactory;
import org.jboss.jsf.factories.CustomClientWindowFactory;
import org.jboss.jsf.factories.CustomExceptionHandlerFactory;
import org.jboss.jsf.factories.CustomExternalContextFactory;
import org.jboss.jsf.factories.CustomFaceletCacheFactory;
import org.jboss.jsf.factories.CustomFacesContextFactory;
import org.jboss.jsf.factories.CustomFlashFactory;
import org.jboss.jsf.factories.CustomFlowHandlerFactory;
import org.jboss.jsf.factories.CustomLifeCycleFactory;
import org.jboss.jsf.factories.CustomPartialViewContextFactory;
import org.jboss.jsf.factories.CustomRenderKitFactory;
import org.jboss.jsf.factories.CustomTagHandlerDelegateFactory;
import org.jboss.jsf.factories.CustomViewDeclarationLanguageFactory;
import org.jboss.jsf.factories.CustomVisitContextFactory;
import org.jboss.jsf.handlers.CustomNavigationHandler;
import org.jboss.jsf.handlers.CustomResourceHandler;
import org.jboss.jsf.handlers.CustomStateManager;
import org.jboss.jsf.listeners.CustomPhaseListener;
import org.jboss.jsf.listeners.CustomSystemEventListener;
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
public class JsfCdiIntegrationTest {

	private final static String WEBINF = "src/main/webapp/WEB-INF";

	@ArquillianResource
	URL url;

	static WebArchive deployment;

	@Deployment(testable = false)
	public static Archive<?> createDeployment() {
		return deployment = ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(CustomELResolver.class.getPackage())
				.addPackage(CustomPhaseListener.class.getPackage())
				.addPackage(CustomNavigationHandler.class.getPackage())
				.addPackage(CustomApplicationFactory.class.getPackage())
				.addPackage(BackingBean.class.getPackage())
				.addAsWebResource(new File("src/main/webapp", "index.html"))
				.addAsWebResource(new File("src/main/webapp", "home.xhtml"))
				.addAsWebResource(new File("src/main/webapp", "second.xhtml"))
				.addAsWebInfResource((new File(WEBINF, "faces-config.xml")))
				.addAsWebInfResource((new File(WEBINF, "web.xml")))
				.addAsWebInfResource(new File(WEBINF, "beans.xml"));

	}

	private String clickOnAction(String id) throws Exception {
		WebClient webClient = new WebClient();
		HtmlPage page = webClient.getPage(url);
		HtmlPage page2 = page.getElementById(id).click();
		return page2.asText();
	}

	@Test
	public void testInjectionToFactories() throws Exception {
		String pageContent = clickOnAction("form:link");
		assertTrue("Injection to "+ CustomApplicationFactory.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomApplicationFactory.class.getName()));
		assertTrue("Injection to "+ CustomClientWindowFactory.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomClientWindowFactory.class.getName()));
		assertTrue("Injection to "+ CustomExceptionHandlerFactory.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomExceptionHandlerFactory.class.getName()));
		assertTrue("Injection to "+ CustomExternalContextFactory.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomExternalContextFactory.class.getName()));
		assertTrue("Injection to "+ CustomFaceletCacheFactory.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomFaceletCacheFactory.class.getName()));
		assertTrue("Injection to "+ CustomFacesContextFactory.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomFacesContextFactory.class.getName()));
		assertTrue("Injection to "+ CustomFlashFactory.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomFlashFactory.class.getName()));
		assertTrue("Injection to "+ CustomFlowHandlerFactory.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomFlowHandlerFactory.class.getName()));
		assertTrue("Injection to "+ CustomLifeCycleFactory.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomLifeCycleFactory.class.getName()));
		assertTrue("Injection to "+ CustomPartialViewContextFactory.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomPartialViewContextFactory.class.getName()));
		assertTrue("Injection to "+ CustomRenderKitFactory.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomRenderKitFactory.class.getName()));
		assertTrue("Injection to "+ CustomTagHandlerDelegateFactory.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomTagHandlerDelegateFactory.class.getName()));
		assertTrue("Injection to "+ CustomViewDeclarationLanguageFactory.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomViewDeclarationLanguageFactory.class.getName()));
		assertTrue("Injection to "+ CustomVisitContextFactory.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomVisitContextFactory.class.getName()));
		
	}
	
	@Test
	public void testInjectionToHandlers() throws Exception {
		String pageContent = clickOnAction("form:link");
		assertTrue("Injection to "+ CustomNavigationHandler.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomNavigationHandler.class.getName()));
		assertTrue("Injection to "+ CustomResourceHandler.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomResourceHandler.class.getName()));
		assertTrue("Injection to "+ CustomStateManager.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomStateManager.class.getName()));
	}
	
	@Test
	public void testInjectionToListeners() throws Exception {
		String pageContent = clickOnAction("form:link");
		assertTrue("Injection to "+ CustomPhaseListener.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomPhaseListener.class.getName()));
		assertTrue("Injection to "+ CustomSystemEventListener.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomSystemEventListener.class.getName()));
	}
	
	@Test
	public void testInjectionToELResolver() throws Exception {
		String pageContent = clickOnAction("form:link");
		assertTrue("Injection to "+ CustomELResolver.class.getSimpleName() +" doesn't work.", pageContent.contains(CustomELResolver.class.getName()));
	}

}
