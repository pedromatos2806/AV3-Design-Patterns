package br.ifba.inf011.aval2.model.state;

import br.ifba.inf011.aval2.model.Arquivo;
import br.ifba.inf011.aval2.model.Credencial;

//ConcreteStates
public class Bloqueado extends EstadoArquivoAbstract {

	public Bloqueado(Arquivo arquivo) {
		super(arquivo);
	}

	@Override
	public String ler(Credencial credencial) throws IllegalAccessException {
		throw new IllegalAccessException("Arquivo está em modo Bloqueado.");
	}

	@Override
	public void escrever(Credencial credencial, String escrever) throws IllegalAccessException {
		throw new IllegalAccessException("Arquivo está em modo Bloqueado.");
	}

	@Override
	public Long getTamanho() throws IllegalAccessException {
		return Long.valueOf(this.arquivo.getConteudo().length());
	}
}
