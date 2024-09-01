package br.ifba.inf011.aval2.model.state;

import br.ifba.inf011.aval2.model.Credencial;
import br.ifba.inf011.aval2.model.EntradaOperavel;

public class Bloqueado extends EstadoArquivoAbstract{

	public Bloqueado(EntradaOperavel arquivo) {
		super(arquivo);
	}

	@Override
	public Long getTamanho() throws IllegalAccessException {
		return arquivo.getTamanho();
	}

	@Override
	public String ler(Credencial credencial) throws IllegalAccessException {
		throw new IllegalAccessException();
	}

	@Override
	public void escrever(Credencial credencial, String escrever) throws IllegalAccessException {
		throw new IllegalAccessException();
	}

}
