package br.ucsal.compiladores;

import java.util.ArrayList;
import java.util.List;

public class TabeladeSimbolos {
	private List<ItemTabelaDeSimbolos> itens;
	private List<Atomo> atomos;

	public List<ItemTabelaDeSimbolos> getItens() {
		return this.itens;
	}
	
	public List<Atomo> getAtomos() {
		return this.atomos;
	}

	public TabeladeSimbolos() {
		this.itens = new ArrayList<ItemTabelaDeSimbolos>();
		this.atomos = new ArrayList<Atomo>();
	}

	public void addItem(ItemTabelaDeSimbolos novoItem) {
		if (itens.contains(novoItem)) {
			for (ItemTabelaDeSimbolos item : itens) {
				if (novoItem.getTipoSimbolo() == item.getTipoSimbolo()) {
					item.addNovaLinha(novoItem.getLinha());
				}
			}	
		} else {
			itens.add(novoItem);
		}
	}
	
	public void addAtomo(Atomo atomo) {
		this.atomos.add(atomo);
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < this.itens.size(); i++) {
			ItemTabelaDeSimbolos currentItem = this.itens.get(i);
			str += "Numero_entrada_tab_simbolos: " + i + " , codigo: " + currentItem.getCodigoAtomo() + 
					" , lexeme: " + currentItem.getLexame()+ " , qnt_caracteres_antes_truncagem: " + currentItem.getQntCaracteresAntesTruncagem() + 
					" , qnt_caracteres_depois_truncagem: " + currentItem.getQntCaracteresDepoisTruncagem() + " , cinco_primeiras_linhas_aparece: " + currentItem.getCincoPrimeirasLinhas().toString()
					+ "\n";
		}
		return str;
	}
}
