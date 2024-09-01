package br.ifba.inf011.aval2.model.memento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ifba.inf011.aval2.model.ArquivoHistorico.ArquivoHistoricoMemento;

public class Caretaker {
	private List<ArquivoHistoricoMemento> mementos = new ArrayList<ArquivoHistoricoMemento>();
	
	public List<ArquivoHistoricoMemento> getHistoryList() {
		return Collections.unmodifiableList(mementos);
	}
	
	public void add(ArquivoHistoricoMemento memento) {
		mementos.add(memento);
	}
	
	public ArquivoHistoricoMemento get(int index) {
		return mementos.get(index);
	}

}
