package br.ifba.inf011.aval2.model.state;

import br.ifba.inf011.aval2.model.Arquivo;
import br.ifba.inf011.aval2.model.Credencial;

public class Normal extends EstadoArquivoAbstract{

	public Normal(Arquivo arquivo) {
		super(arquivo);
	}

	@Override
	public String ler(Credencial credencial) throws IllegalAccessException {
		return this.arquivo.getConteudo();
	}

	@Override
	public void escrever(Credencial credencial, String escrever) throws IllegalAccessException {
		this.arquivo.setConteudo(escrever);
	}

	@Override
	public Long getTamanho() throws IllegalAccessException {
		return Long.valueOf(this.arquivo.getConteudo().length());
	}

}