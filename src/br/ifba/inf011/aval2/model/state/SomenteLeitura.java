package br.ifba.inf011.aval2.model.state;

import br.ifba.inf011.aval2.model.Credencial;
import br.ifba.inf011.aval2.model.EntradaOperavel;

public class SomenteLeitura extends EstadoArquivoAbstract  {

	public SomenteLeitura(EntradaOperavel arquivo) {
		super(arquivo);
	}

	@Override
	public String ler(Credencial credencial) throws IllegalAccessException {
		return arquivo.ler(credencial);
	}
	
	@Override
	public void escrever(Credencial credencial, String escrever) throws IllegalAccessException {
		throw new IllegalAccessException();
	}
	
	@Override
	public Long getTamanho() throws IllegalAccessException{
		return arquivo.getTamanho();
	}

}
