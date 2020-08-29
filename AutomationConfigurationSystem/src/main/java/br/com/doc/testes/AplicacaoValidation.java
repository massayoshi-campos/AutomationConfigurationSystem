package br.com.doc.testes;

import org.junit.Test;

import br.com.doc.common.AplicacaoPage;

public class AplicacaoValidation extends BaseTestCase {
	
	/**
	 * @author Massayohi Campos
	 * Criado em Julho/2020
	 * @throws InterruptedException 
	 * 
	 */	
	@Test
	public void runAllAplicacao() throws InterruptedException {
		Thread.sleep(4000);
		AplicacaoPage.doLogin();
		Thread.sleep(4000);
		AplicacaoPage.doMenu();
		Thread.sleep(2000);
		AplicacaoPage.cadastroAplicacao();
		AplicacaoPage.listAplicacao();
		AplicacaoPage.editAplicacao();
		AplicacaoPage.deleteAplicacao();

	}
}