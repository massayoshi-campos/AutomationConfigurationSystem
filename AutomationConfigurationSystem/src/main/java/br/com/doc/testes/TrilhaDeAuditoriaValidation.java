package br.com.doc.testes;

import org.junit.Test;
import org.sikuli.script.FindFailed;

import br.com.doc.common.TrilhaDeAuditoriaPage;

public class TrilhaDeAuditoriaValidation extends BaseTestCase {
	
	/**
	 * @author Massayohi Campos
	 * Criado em Julho/2020
	 * @throws InterruptedException 
	 * 
	 */	
		@Test
		public void runAllTrilhaAuditoria() throws InterruptedException, FindFailed {
			//TrilhaDeAuditoriaPage.doLogin();
			TrilhaDeAuditoriaPage.doMenu();
			Thread.sleep(1500);
			TrilhaDeAuditoriaPage.doTrilhaPesquisa();
					
	}
	
}
