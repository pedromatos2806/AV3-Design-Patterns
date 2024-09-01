package br.ifba.inf011.aval2.model.state;

import br.ifba.inf011.aval2.model.Arquivo;
import br.ifba.inf011.aval2.model.Credencial;

public class SomenteLeitura extends EstadoArquivoAbstract  {

	public SomenteLeitura(Arquivo arquivo) {
		super(arquivo);
	}

	@Override
	public String ler(Credencial credencial) throws IllegalAccessException {
		return arquivo.getConteudo();
	}
	
	@Override
	public void escrever(Credencial credencial, String escrever) throws IllegalAccessException {
		throw new IllegalAccessException("Arquivo está em modo SomenteLeitura.");
	}
	
	@Override
	public Long getTamanho() throws IllegalAccessException{
		return Long.valueOf(this.arquivo.getConteudo().length());
	}

}
