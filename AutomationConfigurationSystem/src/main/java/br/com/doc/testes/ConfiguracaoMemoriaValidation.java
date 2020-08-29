package br.com.doc.testes;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.sikuli.script.FindFailed;

import br.com.doc.common.ConfiguracaoMemoriaPage;

public class ConfiguracaoMemoriaValidation extends BaseTestCase{
	
	Map<String, String> mapValues = new HashMap<String, String>();
	
	/**
	 * @author Massayohi Campos
	 * Criado em Julho/2020
	 * @throws InterruptedException 
	 * 
	 */
	
	@Test
	public void runAllConfiguracaoMemoria() throws FindFailed {
		//ConfiguracaoMemoriaPage.doLogin();
		ConfiguracaoMemoriaPage.doMenu();
    	ConfiguracaoMemoriaPage.doChave();
		ConfiguracaoMemoriaPage.doTipo();
		ConfiguracaoMemoriaPage.doValor();
		
	}

}
