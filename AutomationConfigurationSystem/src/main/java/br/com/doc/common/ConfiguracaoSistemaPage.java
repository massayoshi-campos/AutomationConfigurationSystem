package br.com.doc.common;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;

import br.com.doc.common.automation.Property;
import br.com.doc.common.automation.Selenium;
import br.com.doc.common.automation.util.RandomStringUtils;
import br.com.doc.common.automation.util.Utils;

public class ConfiguracaoSistemaPage {

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
		
	static Map<String, String> mapValues = new HashMap<String, String>();


	/**
	 * @author Massayohi Campos Criado em Julho/2020
	 * @throws InterruptedException
	 * 
	 */
	
	@Test
	public static void doLogin() {
		
		//Método para clicar no botão login.
		Utils.buttomByIdClick("btn_enter_login");

	}
	
	@Test
	public static void doMenuAdd() {
		
		//Método que clicar no menu lateral.
		Utils.buttomByIdClick("mn_addSystemConfiguration");

	}

	@Test
	public static void doMenu() {
		
		//Método no menu lateral e seleciona a página.
		Utils.buttomByIdClick("mn_management");
		Utils.buttomByIdClick("mn_systemConfiguration");
		Utils.buttomByIdClick("mn_addSystemConfiguration");

	}

	/*
	 * O método abaixo deve cadastrar uma configuração preenchendo o combo "Texto".
	 * Teste para realizar o cadastro das informações utilizando todos os campos. 
	 * O teste deve ser chamado de cadastrado 1.
	 * 
	 */
	
	@Test
	public static void cadastroConfTexto() throws FindFailed, InterruptedException {
		
		Thread.sleep(1500);
		// Método que limpa o campo.
		mapValues.clear();
		
		/* Popula variável para preencher na tela. */
		mapValues.put("systemConfigurationName", "Cadastro Nome Configuração - " + RandomStringUtils.randomNumeric(5));
		
		/* Popula variavel para preencher na tela. */
		mapValues.put("systemConfigurationDataKey", "Cadastro Chave Configuração - " + RandomStringUtils.randomAlphanumeric(5));
		//Utils.fillScreeanById(mapValues);

		/* Preencher o combo na tela. */
		Utils.fillSelect("systemConfigurationType", "Texto");

		/*Popula variável para preencher na tela.*/ 
		mapValues.put("systemConfigurationDescription", "Cadastro Decrição Configuração - " + RandomStringUtils.randomAlphanumeric(5));
		//Utils.fillScreeanById(mapValues);
		
		mapValues.put("systemConfigurationDataValueStr", "Cadastro Valor Massa - " + RandomStringUtils.randomAlphanumeric(5));
		Utils.fillScreeanById(mapValues);
		
		Thread.sleep(1500);
		/*Preencher o combo na tela.*/ 
		Utils.fillSelect("systemConfigurationStatus", "Ativo");
		
		/*Método que clica no botão Adicionar.*/
		Utils.buttomByIdClick("btn_add");
		Thread.sleep(1500);
		
		/*Método que realiza a busca no campo.*/
		mapValues.put("applicationCode", "Teste Automação - Massa");
		Utils.fillScreeanById(mapValues);
		Thread.sleep(1500);
		
		/*Método realiza uma lista de elemento conforme Xpath informado.*/
		List<WebElement> eleCheck = Utils.findElementsByXpath("//*[@id=\"body-doccloud\"]/ngb-modal-window/div/div/app-list-application-modal/div/div[1]/app-list-application/div/fieldset/div[2]/div/div/table/tbody/tr[1]/td[4]/div/div/label");
		if (eleCheck != null && eleCheck.size() > 0) {
			eleCheck.get(0).click();
		}		
		
		/*Método que clica no botão no sistema.*/
		Utils.buttomByIdClick("btn_select");
		Utils.buttomByIdClick("btn_save");

		/*Popula variável para preencher na tela.*/ 
		String msg = Utils.getValueById("toast-container");
		assertThat("Não encontrada a mensagem Alteração efetuada com sucesso", msg, is("Cadastro efetuado com sucesso"));
		
		/*Método que clica na mensagem.*/
		Utils.buttomByIdClick("toast-container");
    	
	}
	
	@Test
	public static void listConfTexto() throws InterruptedException {

		/* Método que clica no menu listar configuração. */
		Utils.buttomByIdClick("mn_listSystemConfiguration");
		
		/*Método que realiza a pesquisa no campo.*/
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("systemConfigurationName", mapValues.get("systemConfigurationName"));		
		mpSearch.put("systemConfigurationDataKey", mapValues.get("systemConfigurationDataKey"));		
		Utils.fillScreeanById(mpSearch);

		/* Pega primeira coluna e linha do resultado da pesquisa. */
		/* Caso não encotre o codigo digitado no cadastro exibe mensagem. */
		Thread.sleep(2000);
		String nome = Utils.getValueById("systemConfigurationName_0");
		String chave = Utils.getValueById("systemConfigurationDatakey_0");
		boolean sucess = nome != null && nome.equals(mpSearch.get("systemConfigurationName")) && chave != null && chave.equals(mpSearch.get("systemConfigurationDataKey"));
		assertThat("Arquivo não encontrado", sucess);
		
		/* Limpar pesquisa. */
		Utils.buttomByIdClick("btn_clean");

	}

	@Test
	public static void editConfTexto() throws InterruptedException {

		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("systemConfigurationName", mapValues.get("systemConfigurationName"));		
		mpSearch.put("systemConfigurationDataKey", mapValues.get("systemConfigurationDataKey"));		
		Utils.fillScreeanById(mpSearch);
		
		/* Pega primeira coluna e linha do resultado da pesquisa. */
		/* Caso não encotre o codigo digitado no cadastro exibe mensagem. */
		String nome = Utils.getValueById("systemConfigurationName_0");
		String chave = Utils.getValueById("systemConfigurationDatakey_0");
		boolean sucess = nome != null && nome.equals(mpSearch.get("systemConfigurationName")) && chave != null && chave.equals(mpSearch.get("systemConfigurationDataKey"));
		assertThat("Arquivo não encontrado", sucess);
		
		/* Redireciona para tela de edição do item clicado. */
		Utils.buttomByIdClick("btn_edit_0");

		/* Limpa variável que preenche na tela. */
		mapValues.clear();
		
		Thread.sleep(1500);
		/* Popula variável para preenchimento na tela. */
		mapValues.put("systemConfigurationName", "Alteração Nome Configuração - " + RandomStringUtils.randomAlphanumeric(7));
		mapValues.put("systemConfigurationDataKey", "Alteração Chave Configuração - " + RandomStringUtils.randomAlphanumeric(7));
		Utils.fillSelect("systemConfigurationType", "Inteiro");
		mapValues.put("systemConfigurationDescription", "Alteração Descrição Configuração - " + RandomStringUtils.randomAlphanumeric(7));
		mapValues.put("systemConfigurationDataValueStr", "Alteração Valor Configuração - " + RandomStringUtils.randomNumeric(10));
		Utils.fillSelect("systemConfigurationStatus", "Inativo");
		Utils.fillScreeanById(mapValues);

		/* Clica no botão salvar.*/
		Utils.buttomByIdClick("btn_save");

		/* Clica no botão Confirmar. */
		Utils.buttomByIdClick("btn_confirm");
		
		String msg = Utils.getValueById("toast-container");
		assertThat("Não encontrada a mensagem Alteração efetuada com sucesso", msg, is("Alteração efetuada com sucesso"));
		
		/*Método que clica na mensagem da tela.*/
		Utils.buttomByIdClick("toast-container");
				
		/* Efetua pesquisa com conteudo que acabou de ser editado. */
		listConfTexto();
				
		/* Limpar pesquisa. */
		Utils.buttomByIdClick("btn_clean");

	}

	@Test
	public static void deleteApplicationTexto() throws InterruptedException {
		/* Efetua pesquisa com conteúdo que acabou de ser editado.*/
		listConfTexto();

		/* Exclui primeiro item do resultado da pesquisa. */
		Utils.buttomByIdClick("btn_delete_0");

		/* Clica no botão de confirmação da exclusão. */
		Utils.buttomByIdClick("btn_confirm");
		
		/*Método que verifica a mensagem do campo.*/
		String msg = Utils.getValueById("toast-container");
		assertThat("Não encontrada a mensagem Exclusão efetuada com sucesso", msg, is("Exclusão efetuada com sucesso"));
		Thread.sleep(2000);
		
		/*Método que clica na mensagem.*/
		Utils.buttomByIdClick("toast-container");

		/* Efetua pesquisa com conteudo que acabou de ser excluído.*/
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("systemConfigurationName", mapValues.get("systemConfigurationName"));		
		mpSearch.put("systemConfigurationDataKey", mapValues.get("systemConfigurationDataKey"));		
		Utils.fillScreeanById(mpSearch);

		// TODO verificar menssage de nenhum item.
		Thread.sleep(2000);
		String msg1 = Utils.getValueById("toast-container");
    	assertThat("Não encontrada a mensagem Nenhum dado encontrado", msg1, is("Nenhum dado encontrado"));
		Thread.sleep(2000);
    	
    	/*Método que clica na mensagem.*/
    	Utils.buttomByIdClick("toast-container");
    	
    	/* Limpar pesquisa.*/
		Utils.buttomByIdClick("btn_clean");
 	
	}

	/*
	 * O método abaixo deve cadastrar uma configuração preenchendo o combo
	 * "Booleano". Teste para realizar o cadastro das informações utilizando todos os campos. 
	 * O teste deve ser chamado de cadastrado 3.
	 */
	
	@Test
	public static void cadastroConfBooleano() throws FindFailed, InterruptedException {
		
		Thread.sleep(1500);
		/*Esperar o menu na tela.*/
		Utils.waitForPageToBeReady("//*[@id=\"body-doccloud\"]/app-root/app-operation/main/div[2]/div");
				
		mapValues.clear();
		/* Popula variavel para preencher na tela. */
		mapValues.put("systemConfigurationName", "Cadastro Nome Configuração - " + RandomStringUtils.randomNumeric(5));
		
		/* Popula variavel para preencher na tela.*/
		mapValues.put("systemConfigurationDataKey", "Cadastro Chave Configuração - " + RandomStringUtils.randomAlphanumeric(5));
		//Utils.fillScreeanById(mapValues);

		/* Preencher o combo na tela. */
		Thread.sleep(1500);
		Utils.waitForPageToBeReady("//*[@id=\"body-doccloud\"]/app-root/app-operation/main/div[2]/div");
		
		Utils.fillSelect("systemConfigurationType", "Booleano");

		/*Popula variavel para preencher na tela.*/ 
		mapValues.put("systemConfigurationDescription", "Cadastro Decrição Configuração - " + RandomStringUtils.randomAlphanumeric(5));
		Utils.fillScreeanById(mapValues);
		
		/*Preencher o Valor na tela.*/
		Utils.fillSelect("systemConfigurationDataValueBoolean", "TRUE");
		
		/*Preencher o combo na tela.*/ 
		Thread.sleep(1500);
		Utils.waitForPageToBeReady("//*[@id=\"body-doccloud\"]/app-root/app-operation/main/div[2]/div");
		
		Utils.fillSelect("systemConfigurationStatus", "Ativo");
		
		Utils.buttomByIdClick("btn_add");
		Thread.sleep(1500);
		
		mapValues.put("applicationCode", "Teste Automação - Massa");
		Utils.fillScreeanById(mapValues);
		
		Thread.sleep(1500);

		List<WebElement> eleCheck = Utils.findElementsByXpath("//*[@id=\"body-doccloud\"]/ngb-modal-window/div/div/app-list-application-modal/div/div[1]/app-list-application/div/fieldset/div[2]/div/div/table/tbody/tr[1]/td[4]/div/div/label");
		if (eleCheck != null && eleCheck.size() > 0) {
			eleCheck.get(0).click();
		}		
		
		Utils.buttomByIdClick("btn_select");
		Utils.buttomByIdClick("btn_save");

		/*Popula variavel para preencher na tela.*/ 
		String msg = Utils.getValueById("toast-container");
		assertThat("Não encontrada a mensagem Alteração efetuada com sucesso", msg, is("Cadastro efetuado com sucesso"));

		Utils.buttomByIdClick("toast-container");
		
	}

	@Test
	public static void listConfBooleano() throws InterruptedException {

		/* Método que clica no menu Listar Configuração. */
		Utils.buttomByIdClick("mn_listSystemConfiguration");
		
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("systemConfigurationName", mapValues.get("systemConfigurationName"));		
		mpSearch.put("systemConfigurationDataKey", mapValues.get("systemConfigurationDataKey"));		
		Utils.fillScreeanById(mpSearch);

		/* Pega primeira coluna e linha do resultado da pesquisa. */
		/* Caso não encotre o codigo digitado no cadastro exibe mensagem. */
		Thread.sleep(2000);
		String nome = Utils.getValueById("systemConfigurationName_0");
		String chave = Utils.getValueById("systemConfigurationDatakey_0");
		boolean sucess = nome != null && nome.equals(mpSearch.get("systemConfigurationName")) && chave != null && chave.equals(mpSearch.get("systemConfigurationDataKey"));
		assertThat("Arquivo não encontrado", sucess);
		
		/* Limpar pesquisa */
		Utils.buttomByIdClick("btn_clean");

	}

	@Test
	public static void editConfBooleano() throws InterruptedException {
		
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("systemConfigurationName", mapValues.get("systemConfigurationName"));		
		mpSearch.put("systemConfigurationDataKey", mapValues.get("systemConfigurationDataKey"));		
		Utils.fillScreeanById(mpSearch);
		
		/* Pega primeira coluna e linha do resultado da pesquisa */
		/* Caso não encotre o codigo digitado no cadastro exibe mensagem */
		String nome = Utils.getValueById("systemConfigurationName_0");
		String chave = Utils.getValueById("systemConfigurationDatakey_0");
		boolean sucess = nome != null && nome.equals(mpSearch.get("systemConfigurationName")) && chave != null && chave.equals(mpSearch.get("systemConfigurationDataKey"));
		assertThat("Arquivo não encontrado", sucess);
		
		/* Redireciona para tela de edição do item clicado */
		Utils.buttomByIdClick("btn_edit_0");

		/* Limpa variavel que preenche na tela */
		mapValues.clear();
		
		Utils.waitForPageToBeReady("//*[@id=\"body-doccloud\"]/app-root/app-operation/main/div[2]/div");
		Thread.sleep(1500);
		/* Popula variavel para preenchimento na tela */
		mapValues.put("systemConfigurationName", "Alteração Nome Configuração - " + RandomStringUtils.randomAlphanumeric(7));
		mapValues.put("systemConfigurationDataKey", "Alteração Chave Configuração - " + RandomStringUtils.randomAlphanumeric(7));
		Utils.fillSelect("systemConfigurationType", "Booleano");
		mapValues.put("systemConfigurationDescription", "Alteração Descrição Configuração - " + RandomStringUtils.randomAlphanumeric(7));
		Utils.fillSelect("systemConfigurationDataValueBoolean", "FALSE");
		Utils.fillSelect("systemConfigurationStatus", "Inativo");
		Utils.fillScreeanById(mapValues);

		/* Clica no botão salvar */
		Utils.buttomByIdClick("btn_save");

		/* Clica no botão Confirmar */
		Utils.buttomByIdClick("btn_confirm");
		
		String msg = Utils.getValueById("toast-container");
		assertThat("Não encontrada a mensagem Alteração efetuada com sucesso", msg, is("Alteração efetuada com sucesso"));
		
		Utils.buttomByIdClick("toast-container");

	}

	@Test
	public static void deleteApplicationBooleano() throws InterruptedException {
		
		/* Efetua pesquisa com conteudo que acabou de ser editado */
		listConfBooleano();

		/* Exclui primeiro item do resultado da pesquisa */
		Utils.buttomByIdClick("btn_delete_0");

		/* Clica no botão de confirmação da exclusão */
		Utils.buttomByIdClick("btn_confirm");
		
		String msg = Utils.getValueById("toast-container");
		assertThat("Não encontrada a mensagem Exclusão efetuada com sucesso", msg, is("Exclusão efetuada com sucesso"));
		
		Utils.buttomByIdClick("toast-container");

		/* Efetua pesquisa com conteudo que acabou de ser excluído */
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("systemConfigurationName", mapValues.get("systemConfigurationName"));		
		mpSearch.put("systemConfigurationDataKey", mapValues.get("systemConfigurationDataKey"));		
		Utils.fillScreeanById(mpSearch);

		// TODO verificar menssage de nenhum item
		String msg1 = Utils.getValueById("toast-container");
    	assertThat("Não encontrada a mensagem Nenhum dado encontrado", msg1, is("Nenhum dado encontrado"));
    	
    	//Clicar no pop-up da mensagem
    	Utils.buttomByIdClick("toast-container");
    	
    	/* Limpar pesquisa */
		Utils.buttomByIdClick("btn_clean");
 
	}

	/*
	 * O método abaixo deve cadastrar uma configuração preenchendo o combo
	 * "Arquivo". Teste para realizar o cadastro das informações utilizando todos os
	 * campos. O teste deve ser chamado de cadastrado 3.
	 * 
	 */
	@Test
	public static void cadastroConfArquivo() throws FindFailed, InterruptedException, AWTException {
		mapValues.clear();
		/* Popula variavel para preencher na tela */
		mapValues.put("systemConfigurationName", "Cadastro Nome Configuração - " + RandomStringUtils.randomNumeric(5));
		/* Preenche o conteudo na tela para Adicionar */

		/* Popula variavel para preencher na tela */
		mapValues.put("systemConfigurationDataKey", "Cadastro Chave Configuração - " + RandomStringUtils.randomAlphanumeric(5));
		//Utils.fillScreeanById(mapValues);

		/* Preencher o combo na tela */
		Utils.fillSelect("systemConfigurationType", "Arquivo");

		/*Popula variavel para preencher na tela*/ 
		mapValues.put("systemConfigurationDescription", "Cadastro Decrição Configuração - " + RandomStringUtils.randomAlphanumeric(5));
		Utils.fillScreeanById(mapValues);
		
		/*Realizar o upload na tela*/
		Utils.buttomByIdClick("btn_upload");
		Thread.sleep(3000);
		Utils.setClipboardArquivo("C:\\dev\\documentos\\Gerar Requisição.pdf");
		Thread.sleep(3000);
		Robot robot =  new  Robot ();
		robot . keyPress ( KeyEvent . VK_CONTROL );
		robot . keyPress ( KeyEvent . VK_V );
		robot . keyRelease ( KeyEvent . VK_V );
		robot . keyRelease ( KeyEvent . VK_CONTROL );
		robot . keyPress ( KeyEvent . VK_ENTER );
		robot . keyRelease ( KeyEvent . VK_ENTER );
		Thread.sleep(3000);
		
		/*Preencher o combo na tela*/ 
		Utils.fillSelect("systemConfigurationStatus", "Ativo");
		
		Utils.buttomByIdClick("btn_add");
		Thread.sleep(2500);
		
		mapValues.put("applicationCode", "Teste Automação - Massa");
		Utils.fillScreeanById(mapValues);
		
		Thread.sleep(2500);

		List<WebElement> eleCheck = Utils.findElementsByXpath("//*[@id=\"body-doccloud\"]/ngb-modal-window/div/div/app-list-application-modal/div/div[1]/app-list-application/div/fieldset/div[2]/div/div/table/tbody/tr[1]/td[4]/div/div/label");
		if (eleCheck != null && eleCheck.size() > 0) {
			eleCheck.get(0).click();
		}		
		
		Utils.buttomByIdClick("btn_select");
		Utils.buttomByIdClick("btn_save");

		/*Popula variavel para preencher na tela*/ 
		String msg = Utils.getValueById("toast-container");
		assertThat("Não encontrada a mensagem Alteração efetuada com sucesso", msg, is("Cadastro efetuado com sucesso"));
		
		Utils.buttomByIdClick("toast-container");


	}

	@Test
	public static void listConfArquivo() throws InterruptedException {
		
		/* Método que clica no menu Listar Configuração */
		Utils.buttomByIdClick("mn_listSystemConfiguration");
		
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("systemConfigurationName", mapValues.get("systemConfigurationName"));		
		mpSearch.put("systemConfigurationDataKey", mapValues.get("systemConfigurationDataKey"));		
		Utils.fillScreeanById(mpSearch);

		/* Pega primeira coluna e linha do resultado da pesquisa */
		/* Caso não encotre o codigo digitado no cadastro exibe mensagem */
		Thread.sleep(2000);
		String nome = Utils.getValueById("systemConfigurationName_0");
		String chave = Utils.getValueById("systemConfigurationDatakey_0");
		boolean sucess = nome != null && nome.equals(mpSearch.get("systemConfigurationName")) && chave != null && chave.equals(mpSearch.get("systemConfigurationDataKey"));
		assertThat("Arquivo não encontrado", sucess);
		
		/* Limpar pesquisa */
		Utils.buttomByIdClick("btn_clean");

	}

	@Test
	public static void editConfArquivo() throws InterruptedException, AWTException {
		
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("systemConfigurationName", mapValues.get("systemConfigurationName"));		
		mpSearch.put("systemConfigurationDataKey", mapValues.get("systemConfigurationDataKey"));		
		Utils.fillScreeanById(mpSearch);
		
		/* Pega primeira coluna e linha do resultado da pesquisa */
		/* Caso não encotre o codigo digitado no cadastro exibe mensagem */
		String nome = Utils.getValueById("systemConfigurationName_0");
		String chave = Utils.getValueById("systemConfigurationDatakey_0");
		boolean sucess = nome != null && nome.equals(mpSearch.get("systemConfigurationName")) && chave != null && chave.equals(mpSearch.get("systemConfigurationDataKey"));
		assertThat("Arquivo não encontrado", sucess);
		
		/* Redireciona para tela de edição do item clicado */
		Utils.buttomByIdClick("btn_edit_0");

		/* Limpa variavel que preenche na tela */
		mapValues.clear();
		
		Thread.sleep(2000);
		/* Popula variavel para preenchimento na tela */
		mapValues.put("systemConfigurationName", "Alteração Nome Configuração - " + RandomStringUtils.randomAlphanumeric(7));
		mapValues.put("systemConfigurationDataKey", "Alteração Chave Configuração - " + RandomStringUtils.randomAlphanumeric(7));
		Utils.fillSelect("systemConfigurationType", "Arquivo");
		mapValues.put("systemConfigurationDescription", "Alteração Descrição Configuração - " + RandomStringUtils.randomAlphanumeric(7));
	
		/*Realizar o upload na tela*/
		Utils.buttomByIdClick("btn_upload");
		Thread.sleep(3000);
		Utils.setClipboardArquivo("C:\\dev\\documentos\\Gerar Requisição.pdf");
		Thread.sleep(3000);
		Robot robot =  new  Robot ();
		robot . keyPress ( KeyEvent . VK_CONTROL );
		robot . keyPress ( KeyEvent . VK_V );
		robot . keyRelease ( KeyEvent . VK_V );
		robot . keyRelease ( KeyEvent . VK_CONTROL );
		robot . keyPress ( KeyEvent . VK_ENTER );
		robot . keyRelease ( KeyEvent . VK_ENTER );
		Thread.sleep(3000);
		
		Utils.fillSelect("systemConfigurationStatus", "Inativo");
		Utils.fillScreeanById(mapValues);

		/* Clica no botão salvar */
		Utils.buttomByIdClick("btn_save");

		/* Clica no botão Confirmar */
		Utils.buttomByIdClick("btn_confirm");
		
		String msg = Utils.getValueById("toast-container");
		assertThat("Não encontrada a mensagem Alteração efetuada com sucesso", msg, is("Alteração efetuada com sucesso"));
		
		Utils.buttomByIdClick("toast-container");

	}

	@Test
	public static void deleteApplicationArquivo() throws InterruptedException {
		Thread.sleep(3000);
		/* Efetua pesquisa com conteudo que acabou de ser editado */
		listConfArquivo();
		
		/* Exclui primeiro item do resultado da pesquisa */
		Thread.sleep(5000);
		Utils.buttomByIdClick("btn_delete_0");
		
		Thread.sleep(4000);
		/* Clica no botão de confirmação da exclusão */
		Utils.buttomByIdClick("btn_confirm");
		
		Thread.sleep(2000);
		String msg = Utils.getValueById("toast-container");
		assertThat("Não encontrada a mensagem Exclusão efetuada com sucesso", msg, is("Exclusão efetuada com sucesso"));
				
		Utils.buttomByIdClick("toast-container");
		Thread.sleep(2000);
		
    	// Efetua pesquisa com conteudo que acabou de ser excluído 
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("systemConfigurationName", mapValues.get("systemConfigurationName"));		
		mpSearch.put("systemConfigurationDataKey", mapValues.get("systemConfigurationDataKey"));		
		Utils.fillScreeanById(mpSearch);
		Thread.sleep(2000);

		// TODO verificar menssage de nenhum item
		String msg1 = Utils.getValueById("toast-container");
    	assertThat("Não encontrada a mensagem Nenhum dado encontrado", msg1, is("Nenhum dado encontrado"));
    	Thread.sleep(2000);
    	
    	Utils.buttomByIdClick("toast-container");
    	
    	
    	/* Limpar pesquisa */
		Utils.buttomByIdClick("btn_clean");
		Thread.sleep(2000);
 	

	}

	/*
	 * O método abaixo deve cadastrar uma configuração preenchendo o combo "Senha".
	 * Teste para realizar o cadastro das informações utilizando todos os campos. O
	 * teste deve ser chamado de cadastrado 3.
	 * 
	 */
	
	@Test
	public static void cadastroConfSenha() throws FindFailed, InterruptedException {
		
		Thread.sleep(2000);
		mapValues.clear();
		/* Popula variavel para preencher na tela */
		mapValues.put("systemConfigurationName", "Cadastro Nome Configuração - " + RandomStringUtils.randomNumeric(5));
		/* Preenche o conteudo na tela para Adicionar */

		/* Popula variavel para preencher na tela */
		mapValues.put("systemConfigurationDataKey", "Cadastro Chave Configuração - " + RandomStringUtils.randomAlphanumeric(5));
		//Utils.fillScreeanById(mapValues);

		/* Preencher o combo na tela */
		Utils.fillSelect("systemConfigurationType", "Senha");

		/*Popula variavel para preencher na tela*/ 
		mapValues.put("systemConfigurationDescription", "Cadastro Decrição Configuração - " + RandomStringUtils.randomAlphanumeric(5));
		Utils.fillScreeanById(mapValues);
		
		/*Popula variavel para preencher na tela*/ 
		mapValues.put("systemConfigurationDataValueStr", ""+ RandomStringUtils.randomNumeric(10));
		Utils.fillScreeanById(mapValues);
				
		/*Preencher o combo na tela*/ 
		Utils.fillSelect("systemConfigurationStatus", "Ativo");
		
		Utils.buttomByIdClick("btn_add");
		Thread.sleep(2000);
		
		mapValues.put("applicationCode", "Teste Automação - Massa");
		Utils.fillScreeanById(mapValues);
		
				
		Thread.sleep(2000);
		List<WebElement> eleCheck = Utils.findElementsByXpath("//*[@id=\"body-doccloud\"]/ngb-modal-window/div/div/app-list-application-modal/div/div[1]/app-list-application/div/fieldset/div[2]/div/div/table/tbody/tr[1]/td[4]/div/div/label");
		if (eleCheck != null && eleCheck.size() > 0) {
			eleCheck.get(0).click();
		}		
		
		Utils.buttomByIdClick("btn_select");
		Utils.buttomByIdClick("btn_save");

		/*Popula variavel para preencher na tela*/ 
		String msg = Utils.getValueById("toast-container");
		assertThat("Não encontrada a mensagem Alteração efetuada com sucesso", msg, is("Cadastro efetuado com sucesso"));
		
		Utils.buttomByIdClick("toast-container");
				
	}

	@Test
	public static void listConfSenha() throws InterruptedException {
		/* Método que clica no menu Listar Configuração */
		Utils.buttomByIdClick("mn_listSystemConfiguration");
		
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("systemConfigurationName", mapValues.get("systemConfigurationName"));		
		mpSearch.put("systemConfigurationDataKey", mapValues.get("systemConfigurationDataKey"));		
		Utils.fillScreeanById(mpSearch);

		/* Pega primeira coluna e linha do resultado da pesquisa */
		/* Caso não encotre o codigo digitado no cadastro exibe mensagem */
		Thread.sleep(2000);
		String nome = Utils.getValueById("systemConfigurationName_0");
		String chave = Utils.getValueById("systemConfigurationDatakey_0");
		boolean sucess = nome != null && nome.equals(mpSearch.get("systemConfigurationName")) && chave != null && chave.equals(mpSearch.get("systemConfigurationDataKey"));
		assertThat("Arquivo não encontrado", sucess);
		
		/* Limpar pesquisa */
		Utils.buttomByIdClick("btn_clean");
				
	}

	@Test
	public static void editConfSenha() throws InterruptedException {
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("systemConfigurationName", mapValues.get("systemConfigurationName"));		
		mpSearch.put("systemConfigurationDataKey", mapValues.get("systemConfigurationDataKey"));		
		Utils.fillScreeanById(mpSearch);
		
		/* Pega primeira coluna e linha do resultado da pesquisa */
		/* Caso não encotre o codigo digitado no cadastro exibe mensagem */
		String nome = Utils.getValueById("systemConfigurationName_0");
		String chave = Utils.getValueById("systemConfigurationDatakey_0");
		boolean sucess = nome != null && nome.equals(mpSearch.get("systemConfigurationName")) && chave != null && chave.equals(mpSearch.get("systemConfigurationDataKey"));
		assertThat("Arquivo não encontrado", sucess);
		
		/* Redireciona para tela de edição do item clicado */
		Utils.buttomByIdClick("btn_edit_0");

		/* Limpa variavel que preenche na tela */
		mapValues.clear();
		
		Thread.sleep(1500);
		/* Popula variavel para preenchimento na tela */
		mapValues.put("systemConfigurationName", "Alteração Nome Configuração - " + RandomStringUtils.randomAlphanumeric(7));
		mapValues.put("systemConfigurationDataKey", "Alteração Chave Configuração - " + RandomStringUtils.randomAlphanumeric(7));
		Utils.fillSelect("systemConfigurationType", "Senha");
		mapValues.put("systemConfigurationDescription", "Alteração Descrição Configuração - " + RandomStringUtils.randomAlphanumeric(7));
		mapValues.put("systemConfigurationDataValueStr", "" + RandomStringUtils.randomNumeric(10));
		Utils.fillSelect("systemConfigurationStatus", "Inativo");
		Utils.fillScreeanById(mapValues);

		/* Clica no botão salvar */
		Utils.buttomByIdClick("btn_save");

		/* Clica no botão Confirmar */
		Utils.buttomByIdClick("btn_confirm");
		
		String msg = Utils.getValueById("toast-container");
		assertThat("Não encontrada a mensagem Alteração efetuada com sucesso", msg, is("Alteração efetuada com sucesso"));
		
		Utils.buttomByIdClick("toast-container");
							
	}

	@Test
	public static void deleteApplicationSenha() throws InterruptedException {
		/* Efetua pesquisa com conteudo que acabou de ser editado */
		listConfSenha();

		/* Exclui primeiro item do resultado da pesquisa */
		Utils.buttomByIdClick("btn_delete_0");

		/* Clica no botão de confirmação da exclusão */
		Utils.buttomByIdClick("btn_confirm");
		
		String msg = Utils.getValueById("toast-container");
		assertThat("Não encontrada a mensagem Exclusão efetuada com sucesso", msg, is("Exclusão efetuada com sucesso"));
		Thread.sleep(1500);
		
		Utils.buttomByIdClick("toast-container");

		/* Efetua pesquisa com conteudo que acabou de ser excluído */
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("systemConfigurationName", mapValues.get("systemConfigurationName"));		
		mpSearch.put("systemConfigurationDataKey", mapValues.get("systemConfigurationDataKey"));		
		Utils.fillScreeanById(mpSearch);

		// TODO verificar menssage de nenhum item
		Thread.sleep(2000);
		String msg1 = Utils.getValueById("toast-container");
    	assertThat("Não encontrada a mensagem Nenhum dado encontrado", msg1, is("Nenhum dado encontrado"));
    	Thread.sleep(2000);
    	
    	Utils.buttomByIdClick("toast-container");
    	
    	/* Limpar pesquisa */
		Utils.buttomByIdClick("btn_clean");
 	
		
	}
		
}
