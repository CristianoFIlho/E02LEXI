package br.ucsal.compiladores;

import java.util.ArrayList;
import java.util.List;

public class RelatorioLexico {
	List<ItemTabelaDeSimbolos> itens;
	TabeladeSimbolos tabeladeSimbolos;

	public RelatorioLexico() {
		this.itens = new ArrayList<ItemTabelaDeSimbolos>();
		this.tabeladeSimbolos = new TabeladeSimbolos();
	}

	public void addItem(ItemTabelaDeSimbolos item) {
		this.itens.add(item);
	}
	
	public TabeladeSimbolos getTabeladeSimbolos() {
		return this.tabeladeSimbolos;
	}
	
	public List<ItemTabelaDeSimbolos> getItens() {
		return this.itens;
	}

}
