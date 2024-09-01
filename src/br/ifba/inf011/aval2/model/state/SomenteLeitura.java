package br.ifba.inf011.aval2.model.state;

import br.ifba.inf011.aval2.model.Credencial;

public class SomenteLeitura extends EstadoArquivoAbstract  {

	@Override
	public String ler(Credencial credencial) throws IllegalAccessException {
		return  getArquivo().ler(credencial);
	}
	
	@Override
	public void escrever(Credencial credencial, String escrever) throws IllegalAccessException {
		throw new IllegalAccessException();
	}
	
	@Override
	public Long getTamanho() throws IllegalAccessException{
		return getArquivo().getTamanho();
	}

}
