package br.ifba.inf011.aval2.model.memento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ifba.inf011.aval2.model.ArquivoHistorico.ArquivoHistoricoMemento;

//Caretaker
public class Caretaker {
	private List<Memento> mementos = new ArrayList<Memento>();
	
	public List<Memento> getHistoryList() {
		return Collections.unmodifiableList(mementos);
	}
	
	public void add(ArquivoHistoricoMemento memento) {
		mementos.add(memento);
	}
	
	public Memento get(int index) {
		return mementos.get(index);
	}

}
