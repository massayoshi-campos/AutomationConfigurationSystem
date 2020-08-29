package br.com.doc.common;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;

import br.com.doc.common.automation.Property;
import br.com.doc.common.automation.Selenium;
import br.com.doc.common.automation.util.Utils;

public class TrilhaDeAuditoriaPage {
	
	
	/**
	 * Instância privada do webdriver que inicia a suíte de teste.
	 */
	private static final WebDriver driver;
	private static final WebDriverWait wait;

	/**
	 * Construtor que adicionar a instância do webdriver para utilização dos métodos.
	 * 
	 */
	
	static {
		driver = Selenium.getDriver();
		wait = new WebDriverWait(driver, 40);
		driver.navigate().to(Property.SITE_ADDRESS);
	
	}
	
	static Map<String, String> mapValues = new HashMap<String, String>();


	/**
	 * @author Massayohi Campos Criado em Julho/2020.
	 * @throws InterruptedException
	 * 
	 */
	
	@Test
	public static void doLogin() {
		
		//Método que clicar no botão do Login.
		Utils.buttomByIdClick("btn_enter_login");

	}
	
	
	@Test
	public static void doMenu() {
		
		//Método que clicar no menu lateral.
		Utils.buttomByIdClick("mn_audit");
		Utils.buttomByIdClick("mn_auditTrail"); 
		Utils.buttomByIdClick("mn_listAuditTrail");

	}
	
	@Test
	public static void doTrilhaPesquisa() throws FindFailed, InterruptedException {
		
		//Método que clicar no menu da barra lateral.
		mapValues.put("auditTrailFullNameUserSystem", "Uezio");
		Utils.fillScreeanById(mapValues);
		
		mapValues.put("auditTrailServiceName", "/services/private/deleteApplication");
		Utils.fillScreeanById(mapValues);
		
		Thread.sleep(1500);
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("auditTrailFullNameUserSystem", mapValues.get("auditTrailFullNameUserSystem"));		
		mpSearch.put("auditTrailServiceName", mapValues.get("auditTrailServiceName"));		
		Utils.fillScreeanById(mpSearch);
		
		String nome = Utils.getValueById("auditTrailFullNameUserSystem_0");
		String chave = Utils.getValueById("auditTrailServiceName_0");
		boolean sucess = nome != null && nome.equals(mpSearch.get("auditTrailFullNameUserSystem")) && chave != null && chave.equals(mpSearch.get("auditTrailServiceName"));
		assertThat("Arquivo não encontrado", sucess);
		
		Utils.buttomByIdClick("btn_view_0");
		
		Utils.waitForPageToBeReady("//*[@id=\"auditTrailFullNameUserSystem\"]");
		
		String name = Utils.getValueDisabledById("auditTrailFullNameUserSystem");
		Thread.sleep(1500);
		String services = Utils.getValueDisabledById("auditTrailServiceName");
		Thread.sleep(1500);
		String document = Utils.getValueDisabledById("auditTrailUserSystemDocumentNumber");
		Thread.sleep(2500);
		
		Utils.buttomByIdClick("btn_cancel");
		Thread.sleep(1500);
		Utils.buttomByIdClick("btn_clean");
		
	}	
		
}
