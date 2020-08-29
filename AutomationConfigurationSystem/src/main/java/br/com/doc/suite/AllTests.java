package br.com.doc.suite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;

import br.com.doc.common.automation.Property;
import br.com.doc.common.automation.Selenium;
import br.com.doc.testes.AplicacaoValidation;
import br.com.doc.testes.ConfiguracaoMemoriaValidation;
import br.com.doc.testes.ConfiguracaoSistemaValidation;
import br.com.doc.testes.TrilhaDeAuditoriaValidation;

@RunWith(Suite.class)
@Suite.SuiteClasses({

		AplicacaoValidation.class,
		ConfiguracaoSistemaValidation.class,
		ConfiguracaoMemoriaValidation.class,
		TrilhaDeAuditoriaValidation.class,
		
})

public class AllTests {

	protected static WebDriver driver;

	public static Boolean isAllTestsExecution = false;

	@BeforeClass
	public static void beforeClass() throws Exception {
		isAllTestsExecution = true;
		driver = Selenium.getDriver();
		driver.navigate().to(Property.SITE_ADDRESS);
		driver.manage().window().maximize();
		
	}

	@AfterClass
	public static void afterClass() throws Exception {
		driver.quit();
	}

}
