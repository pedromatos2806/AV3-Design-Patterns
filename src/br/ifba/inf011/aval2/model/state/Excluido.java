package br.ifba.inf011.aval2.model.state;

import br.ifba.inf011.aval2.model.Arquivo;
import br.ifba.inf011.aval2.model.Credencial;

//ConcreteStates
public class Excluido extends EstadoArquivoAbstract {

	public Excluido(Arquivo arquivo) {
		super(arquivo);
	}

	@Override
	public String ler(Credencial credencial) throws IllegalAccessException {
		throw new IllegalAccessException("Arquivo está em modo Excluído.");
	}

	@Override
	public void escrever(Credencial credencial, String escrever) throws IllegalAccessException {
		throw new IllegalAccessException("Arquivo está em modo Excluído.");
	}

	@Override
	public Long getTamanho() throws IllegalAccessException {
		return 0L;
	}
}
