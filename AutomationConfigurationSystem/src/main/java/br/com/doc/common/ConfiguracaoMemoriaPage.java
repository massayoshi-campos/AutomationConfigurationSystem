package br.com.doc.common;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.doc.common.automation.Property;
import br.com.doc.common.automation.Selenium;
import br.com.doc.common.automation.util.Utils;

public class ConfiguracaoMemoriaPage {

	/**
	 * Instância privada do webdriver que inicia a suíte de teste.
	 */
	private static final WebDriver driver;
	private static final WebDriverWait wait;

	/**
	 * Construtor que adicionar a instância do webdriver para utilização dos métodos.
	 */
	static {
		driver = Selenium.getDriver();
		wait = new WebDriverWait(driver, 40);
		driver.navigate().to(Property.SITE_ADDRESS);
		
	}
	
    Map<String, String> mapValues = new HashMap<String, String>();

	/**
	 * @author Massayohi Campos Criado em julho/2020
	 * 
	 */
	@Test
	public static void doLogin() {
		
		//Método para clicar no botão login.
		Utils.buttomByIdClick("btn_enter_login");
	
	}

	@Test
	public static void doMenu() {
		
		//Método para clicar no menu lateral.
		Utils.buttomByIdClick("mn_management");
		Utils.buttomByIdClick("mn_systemConfiguration");
		Utils.buttomByIdClick("mn_listSystemConfigurationMemory");

	}

	@Test
	public static void doChave() {
		
		//Método que pega a mensagem do campo.
		String msg = Utils.getValueById("systemConfigurationDatakey_0");

		/* Valida se tem a mensagem de cadastro com sucesso. */
		assertThat("O conteúdo diferente do esperado", msg, is("iam.service.address"));
			
		msg = Utils.getValueById("systemConfigurationDatakey_1");
		assertThat("O conteúdo diferente do esperado", msg, is("internal.service.network.mask"));
		msg = Utils.getValueById("systemConfigurationDatakey_2");
		assertThat("O conteúdo diferente do esperado", msg, is("rabbitmq.address"));
		msg = Utils.getValueById("systemConfigurationDatakey_3");
		assertThat("O conteúdo diferente do esperado", msg, is("rabbitmq.doc.conf.exchange.name"));
		msg = Utils.getValueById("systemConfigurationDatakey_4");
		assertThat("O conteúdo diferente do esperado", msg, is("rabbitmq.doc.conf.queue.name"));
		msg = Utils.getValueById("systemConfigurationDatakey_5");
		assertThat("O conteúdo diferente do esperado", msg, is("rabbitmq.password"));
		msg = Utils.getValueById("systemConfigurationDatakey_6");
		assertThat("O conteúdo diferente do esperado", msg, is("rabbitmq.port"));
		msg = Utils.getValueById("systemConfigurationDatakey_7");
		assertThat("O conteúdo diferente do esperado", msg, is("rabbitmq.username"));

	}

	@Test
	public static void doTipo() {
		
		//Método que pega a mensagem do campo.
		String msg = Utils.getValueById("systemConfigurationType_0");

		/* Valida se tem a mensagem de cadastro com sucesso. */
		assertThat("O conteúdo diferente do esperado", msg, is("Texto"));

		msg = Utils.getValueById("systemConfigurationType_1");
		assertThat("O conteúdo diferente do esperado", msg, is("Texto"));
		msg = Utils.getValueById("systemConfigurationType_2");
		assertThat("O conteúdo diferente do esperado", msg, is("Texto"));
		msg = Utils.getValueById("systemConfigurationType_3");
		assertThat("O conteúdo diferente do esperado", msg, is("Texto"));
		msg = Utils.getValueById("systemConfigurationType_4");
		assertThat("O conteúdo diferente do esperado", msg, is("Texto"));
		msg = Utils.getValueById("systemConfigurationType_5");
		assertThat("O conteúdo diferente do esperado", msg, is("Senha"));
		msg = Utils.getValueById("systemConfigurationType_6");
		assertThat("O conteúdo diferente do esperado", msg, is("Inteiro"));
		msg = Utils.getValueById("systemConfigurationType_7");
		assertThat("O conteúdo diferente do esperado", msg, is("Texto"));

	}

	@Test
	public static void doValor() {
		
		//Método que pega a mensagem do campo.
		String msg = Utils.getValueById("systemConfigurationDataValue_0");
		
		/*Valida se tem a mensagem de cadastro com sucesso.*/
		assertThat("O conteúdo diferente do esperado", msg, is("http://qa-iam-ws.qa.doccloud.com.br/doc-iam-ws"));
		
		msg = Utils.getValueById("systemConfigurationDataValue_1");
		assertThat("O conteúdo diferente do esperado", msg, is("0.0.0.0/0"));
		msg = Utils.getValueById("systemConfigurationDataValue_2");
		assertThat("O conteúdo diferente do esperado", msg, is("docpdevd02.doc.int"));
		msg = Utils.getValueById("systemConfigurationDataValue_3");
		assertThat("O conteúdo diferente do esperado", msg, is("DOC_CONF_EXCHANGE_QA"));
		msg = Utils.getValueById("systemConfigurationDataValue_4");
		assertThat("O conteúdo diferente do esperado", msg, is("DOC_CONF_QUEUE_QA"));
		msg = Utils.getValueById("systemConfigurationDataValue_6");
		assertThat("O conteúdo diferente do esperado", msg, is("5672"));
		msg = Utils.getValueById("systemConfigurationDataValue_7");
		assertThat("O conteúdo diferente do esperado", msg, is("admin"));
	}
	
}
