package br.com.doc.testes;

import java.awt.AWTException;

import org.junit.Test;
import org.sikuli.script.FindFailed;

import br.com.doc.common.ConfiguracaoSistemaPage;


public class ConfiguracaoSistemaValidation extends BaseTestCase {

	/**
	 * @author Massayohi Campos
	 * Criado em Julho/2020
	 * @throws InterruptedException 
	 * 
	 */
	
	@Test
	public void TestA() throws FindFailed, InterruptedException, AWTException {
		//Cadastro do texto
		//ConfiguracaoSistemaPage.doLogin();
		ConfiguracaoSistemaPage.doMenu();
		Thread.sleep(2000);
		ConfiguracaoSistemaPage.cadastroConfTexto();
		ConfiguracaoSistemaPage.listConfTexto();
		ConfiguracaoSistemaPage.editConfTexto();
		ConfiguracaoSistemaPage.deleteApplicationTexto();
			
	}	
	
	@Test
	public void TestB() throws FindFailed, InterruptedException, AWTException {	
	//Cadastro do Booleano
		Thread.sleep(1500);
		ConfiguracaoSistemaPage.doMenuAdd();
		Thread.sleep(2000);
		ConfiguracaoSistemaPage.cadastroConfBooleano();
		ConfiguracaoSistemaPage.listConfBooleano();
		ConfiguracaoSistemaPage.editConfBooleano();
		ConfiguracaoSistemaPage.deleteApplicationBooleano();
			
	} 
		
	@Test
	public void TestC() throws FindFailed, InterruptedException, AWTException {	
	//Cadastro do Arquivo
		Thread.sleep(1500);
		ConfiguracaoSistemaPage.doMenuAdd();
		Thread.sleep(2000);
		ConfiguracaoSistemaPage.cadastroConfArquivo();
		ConfiguracaoSistemaPage.listConfArquivo();
		ConfiguracaoSistemaPage.editConfArquivo();
		ConfiguracaoSistemaPage.deleteApplicationArquivo();
				
	}	

	@Test
	public void TestD() throws FindFailed, InterruptedException, AWTException {
	//Cadastro da Senha
		Thread.sleep(1500);
		ConfiguracaoSistemaPage.doMenuAdd();
		Thread.sleep(2000);
		ConfiguracaoSistemaPage.cadastroConfSenha();
		ConfiguracaoSistemaPage.listConfSenha();
		ConfiguracaoSistemaPage.editConfSenha();
		ConfiguracaoSistemaPage.deleteApplicationSenha();
		
	}	

}