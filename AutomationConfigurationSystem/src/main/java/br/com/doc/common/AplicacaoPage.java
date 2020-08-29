package br.com.doc.common;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.doc.common.automation.Property;
import br.com.doc.common.automation.Selenium;
import br.com.doc.common.automation.util.RandomStringUtils;
import br.com.doc.common.automation.util.Utils;

public class AplicacaoPage {

	/**
	 * Inst�ncia privada do webdriver que inicia a su�te de teste.
	 */
	private static final WebDriver driver;
	private static final WebDriverWait wait;

	/**
	 * Construtor que adicionar a inst�ncia do webdriver para utiliza��o dos m�todos.
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
	public static void doLogin() throws InterruptedException {
		
		//M�todo que escolhe o usu�rio e clicar no bot�o 'Login'.
		
		Utils.buttomByIdClick("login_user_1");
		Thread.sleep(2000);
		Utils.buttomByIdClick("btn_enter_login");

	}

	@Test
	public static void doMenu() throws InterruptedException {
		
		//M�todo que clicar no menu lateral e seleciona a p�gina aplica��o.
		 
		Thread.sleep(2000);
		Utils.buttomByIdClick("mn_management");
		Utils.buttomByIdClick("mn_application");
		Utils.buttomByIdClick("mn_addApplication");

	}
		
	/*
	 * O m�todo abaixo deve cadastrar uma aplica��o.
	 * Teste para realizar o cadastro das informa��es utilizando todos os campos. 
	 * O teste deve ser chamado de cadastrado 1.
	 * 
	 */
	
	@Test
	public static void cadastroAplicacao() {
		
		// Realiza a limpeza dos campos na tela.
		mapValues.clear();
		
		/* Popula a vari�vel para realizar o preenchimento na tela. */
		mapValues.put("applicationCode", "Cadastro Nome Aplica��o - " + RandomStringUtils.randomAlphanumeric(8));
		
		/* Preenche o conte�do na tela para adicionar. */
		Utils.fillScreeanById(mapValues);
		
		//Clica no bot�o salvar.	
		Utils.buttomByIdClick("btn_save");
		
		/*M�todo que v�lida a mensagem na tela.*/
		String msg = Utils.getValueById("toast-container");
		assertThat("N�o encontrada a mensagem - Cadastro efetuado com sucesso", msg, is("Cadastro efetuado com sucesso"));
		
		/*O m�todo clica na mensagem.*/
		Utils.buttomByIdClick("toast-container");
		
	}
	
	/*
	 *  O m�todo abaixo deve validar o cadastro rec�m realizado.
	 *  
	 */
	@Test
	public static void listAplicacao() throws InterruptedException {
		
		/* M�todo que clica no menu listar configura��o. */
		Utils.buttomByIdClick("mn_listApplication");
		Thread.sleep(1500);
    	
		/*M�todo para realizar a pesquisa e valida o cont�udo na coluna*/
		/* Caso n�o encontre o c�digo digitado no cadastro exibe mensagem */
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("applicationCode", mapValues.get("applicationCode"));
		Utils.fillScreeanById(mpSearch);
		Thread.sleep(1500);
		String applicationCode = Utils.getValueById("applicationCode_0");
		boolean sucess = applicationCode != null && applicationCode.equals(mpSearch.get("applicationCode"));
		assertThat("Arquivo n�o encontrado", sucess);
	
	}
	
	/*
	 * M�todo para realizar a edi��o dos itens rec�m cadastrado.
	 */
	@Test
	public static void editAplicacao()  {
		/*M�todo para realizar a pesquisa e valida o cont�udo na coluna.*/
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("applicationCode", mapValues.get("applicationCode"));		
		Utils.fillScreeanById(mpSearch);
		
		/* Pega primeira coluna e linha do resultado da pesquisa.*/
		/* Caso n�o encontre o c�digo digitado no cadastro exibe mensagem.*/
		String applicationCode = Utils.getValueById("applicationCode_0");
		boolean sucess = applicationCode != null && applicationCode.equals(mpSearch.get("applicationCode"));
		assertThat("Arquivo n�o encontrado", sucess);
		
		/* Redireciona para tela de edi��o do item clicado. */
		Utils.buttomByIdClick("btn_edit_0");

		/* Limpa vari�vel que preenche na tela. */
		mapValues.clear();
		
		/* Popula vari�vel para preencher na tela. */
		mapValues.put("applicationCode", "Altera��o Nome Aplica��o - " + RandomStringUtils.randomAlphanumeric(10));
		
		/* Preenche o conteudo na tela para Adicionar.*/
		Utils.fillScreeanById(mapValues);
		
		/* Clica no bot�o Salvar. */
		Utils.buttomByIdClick("btn_save");
		Utils.buttomByIdClick("btn_confirm");
		
		/*Valida a mensagem de sucesso.*/
		String msg = Utils.getValueById("toast-container");
		assertThat("N�o encontrada a mensagem - Cadastro efetuado com sucesso", msg, is("Altera��o efetuada com sucesso"));
		
		/*M�todo clica na mensagem para ela desaparecer.*/
		Utils.buttomByIdClick("toast-container");
		
	}
	
	/*
	 * O m�todo realiza a exclus�o da aplica��o na tela.
	 */
	@Test
	public static void deleteAplicacao() throws InterruptedException  {
		/*O sistema vai realizar a pesquisa na tela*/
		listAplicacao();
		
		/* Exclui primeiro item do resultado da pesquisa */
		Utils.buttomByIdClick("btn_delete_0");

		/* Clica no bot�o de confirma��o da exclus�o */
		Utils.buttomByIdClick("btn_confirm");
		Thread.sleep(1500);
    	
		//Efetua pesquisa com conte�do que acabou de ser editado. 
		Map<String, String> mpSearch1 = new HashMap<String, String>();
		mpSearch1.put("applicationCode", mapValues.get("applicationCode"));		
		Utils.fillScreeanById(mpSearch1);

		// TODO verificar menssage de nenhum item.
		String msg = Utils.getValueById("toast-container");
    	assertThat("N�o encontrada a mensagem Nenhum dado encontrado", msg, is("Nenhum dado encontrado\nExclus�o efetuada com sucesso"));
    	Thread.sleep(1500);
    	
    	/*M�todo clica na mensagem para ela desaparecer.*/
    	Utils.buttomByIdClick("toast-container");
    	Thread.sleep(1000);
    	Utils.buttomByIdClick("toast-container");
    	
    	/* Limpar pesquisa. */
		Utils.buttomByIdClick("btn_clean");
		
	}

}