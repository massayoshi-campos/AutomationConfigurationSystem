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
	 * @author Massayohi Campos Criado em Julho/2020.
	 * @throws InterruptedException
	 * 
	 */
	
	@Test
	public static void doLogin() throws InterruptedException {
		
		//Método que escolhe o usuário e clicar no botão 'Login'.
		
		Utils.buttomByIdClick("login_user_1");
		Thread.sleep(2000);
		Utils.buttomByIdClick("btn_enter_login");

	}

	@Test
	public static void doMenu() throws InterruptedException {
		
		//Método que clicar no menu lateral e seleciona a página aplicação.
		 
		Thread.sleep(2000);
		Utils.buttomByIdClick("mn_management");
		Utils.buttomByIdClick("mn_application");
		Utils.buttomByIdClick("mn_addApplication");

	}
		
	/*
	 * O método abaixo deve cadastrar uma aplicação.
	 * Teste para realizar o cadastro das informações utilizando todos os campos. 
	 * O teste deve ser chamado de cadastrado 1.
	 * 
	 */
	
	@Test
	public static void cadastroAplicacao() {
		
		// Realiza a limpeza dos campos na tela.
		mapValues.clear();
		
		/* Popula a variável para realizar o preenchimento na tela. */
		mapValues.put("applicationCode", "Cadastro Nome Aplicação - " + RandomStringUtils.randomAlphanumeric(8));
		
		/* Preenche o conteúdo na tela para adicionar. */
		Utils.fillScreeanById(mapValues);
		
		//Clica no botão salvar.	
		Utils.buttomByIdClick("btn_save");
		
		/*Método que válida a mensagem na tela.*/
		String msg = Utils.getValueById("toast-container");
		assertThat("Não encontrada a mensagem - Cadastro efetuado com sucesso", msg, is("Cadastro efetuado com sucesso"));
		
		/*O método clica na mensagem.*/
		Utils.buttomByIdClick("toast-container");
		
	}
	
	/*
	 *  O método abaixo deve validar o cadastro recém realizado.
	 *  
	 */
	@Test
	public static void listAplicacao() throws InterruptedException {
		
		/* Método que clica no menu listar configuração. */
		Utils.buttomByIdClick("mn_listApplication");
		Thread.sleep(1500);
    	
		/*Método para realizar a pesquisa e valida o contéudo na coluna*/
		/* Caso não encontre o código digitado no cadastro exibe mensagem */
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("applicationCode", mapValues.get("applicationCode"));
		Utils.fillScreeanById(mpSearch);
		Thread.sleep(1500);
		String applicationCode = Utils.getValueById("applicationCode_0");
		boolean sucess = applicationCode != null && applicationCode.equals(mpSearch.get("applicationCode"));
		assertThat("Arquivo não encontrado", sucess);
	
	}
	
	/*
	 * Método para realizar a edição dos itens recém cadastrado.
	 */
	@Test
	public static void editAplicacao()  {
		/*Método para realizar a pesquisa e valida o contéudo na coluna.*/
		Map<String, String> mpSearch = new HashMap<String, String>();
		mpSearch.put("applicationCode", mapValues.get("applicationCode"));		
		Utils.fillScreeanById(mpSearch);
		
		/* Pega primeira coluna e linha do resultado da pesquisa.*/
		/* Caso não encontre o código digitado no cadastro exibe mensagem.*/
		String applicationCode = Utils.getValueById("applicationCode_0");
		boolean sucess = applicationCode != null && applicationCode.equals(mpSearch.get("applicationCode"));
		assertThat("Arquivo não encontrado", sucess);
		
		/* Redireciona para tela de edição do item clicado. */
		Utils.buttomByIdClick("btn_edit_0");

		/* Limpa variável que preenche na tela. */
		mapValues.clear();
		
		/* Popula variável para preencher na tela. */
		mapValues.put("applicationCode", "Alteração Nome Aplicação - " + RandomStringUtils.randomAlphanumeric(10));
		
		/* Preenche o conteudo na tela para Adicionar.*/
		Utils.fillScreeanById(mapValues);
		
		/* Clica no botão Salvar. */
		Utils.buttomByIdClick("btn_save");
		Utils.buttomByIdClick("btn_confirm");
		
		/*Valida a mensagem de sucesso.*/
		String msg = Utils.getValueById("toast-container");
		assertThat("Não encontrada a mensagem - Cadastro efetuado com sucesso", msg, is("Alteração efetuada com sucesso"));
		
		/*Método clica na mensagem para ela desaparecer.*/
		Utils.buttomByIdClick("toast-container");
		
	}
	
	/*
	 * O método realiza a exclusão da aplicação na tela.
	 */
	@Test
	public static void deleteAplicacao() throws InterruptedException  {
		/*O sistema vai realizar a pesquisa na tela*/
		listAplicacao();
		
		/* Exclui primeiro item do resultado da pesquisa */
		Utils.buttomByIdClick("btn_delete_0");

		/* Clica no botão de confirmação da exclusão */
		Utils.buttomByIdClick("btn_confirm");
		Thread.sleep(1500);
    	
		//Efetua pesquisa com conteúdo que acabou de ser editado. 
		Map<String, String> mpSearch1 = new HashMap<String, String>();
		mpSearch1.put("applicationCode", mapValues.get("applicationCode"));		
		Utils.fillScreeanById(mpSearch1);

		// TODO verificar menssage de nenhum item.
		String msg = Utils.getValueById("toast-container");
    	assertThat("Não encontrada a mensagem Nenhum dado encontrado", msg, is("Nenhum dado encontrado\nExclusão efetuada com sucesso"));
    	Thread.sleep(1500);
    	
    	/*Método clica na mensagem para ela desaparecer.*/
    	Utils.buttomByIdClick("toast-container");
    	Thread.sleep(1000);
    	Utils.buttomByIdClick("toast-container");
    	
    	/* Limpar pesquisa. */
		Utils.buttomByIdClick("btn_clean");
		
	}

}