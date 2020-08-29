package br.com.doc.testes;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import br.com.doc.common.automation.Property;
import br.com.doc.common.automation.Selenium;
import br.com.doc.suite.AllTests;

public class BaseTestCase {
	
	protected static WebDriver driver;

	@BeforeClass
	public static void beforeClass() throws Exception {
		if (!AllTests.isAllTestsExecution) {
			driver = Selenium.getDriver();
			driver.navigate().to(Property.SITE_ADDRESS);
			driver.manage().window().maximize();
			/*driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);*/
		}
	}

	@AfterClass
	public static void afterClass() throws Exception {
		if (!AllTests.isAllTestsExecution) {
			//driver.quit();
		}
	}

}
