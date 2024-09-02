package br.ifba.inf011.aval2.model.state;

import br.ifba.inf011.aval2.model.Arquivo;
import br.ifba.inf011.aval2.model.Credencial;
import br.ifba.inf011.aval2.model.Operavel;

//State
public abstract class EstadoArquivoAbstract implements Operavel {

	protected Arquivo arquivo;

	protected EstadoArquivoAbstract(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public String dump() {
		return this.arquivo.dump();
	}

	public abstract String ler(Credencial credencial) throws IllegalAccessException;

	public abstract void escrever(Credencial credencial, String escrever) throws IllegalAccessException;

	public abstract Long getTamanho() throws IllegalAccessException;

}
