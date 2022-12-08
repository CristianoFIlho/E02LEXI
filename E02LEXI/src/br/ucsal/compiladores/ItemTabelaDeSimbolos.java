package br.ucsal.compiladores;

import java.util.ArrayList;
import java.util.List;

public class ItemTabelaDeSimbolos {
	private Integer numEntradaTabSimbolos;

	private String codigoAtomo;

	private String lexame;

	private Integer qntCaracteresAntesTruncagem;

	private Integer qntCaracteresDepoisTruncagem;

	private Atomo tipoSimbolo;

	private Integer linha;

	private List<Integer> cincoPrimeirasLinhas;


	public Integer getNumEntradaTabSimbolos() {
		return numEntradaTabSimbolos;
	}

	public void setNumEntradaTabSimbolos(Integer numEntradaTabSimbolos) {
		this.numEntradaTabSimbolos = numEntradaTabSimbolos;
	}

	public String getCodigoAtomo() {
		return codigoAtomo;
	}

	public void setCodigoAtomo(String codigoAtomo) {
		this.codigoAtomo = codigoAtomo;
	}

	public String getLexame() {
		return lexame;
	}

	public void setLexame(String lexame) {
		this.lexame = lexame;
	}

	public Integer getQntCaracteresAntesTruncagem() {
		return qntCaracteresAntesTruncagem;
	}

	public void setQntCaracteresAntesTruncagem(Integer qntCaracteresAntesTruncagem) {
		this.qntCaracteresAntesTruncagem = qntCaracteresAntesTruncagem;
	}

	public Integer getQntCaracteresDepoisTruncagem() {
		return qntCaracteresDepoisTruncagem;
	}

	public void setQntCaracteresDepoisTruncagem(Integer qntCaracteresDepoisTruncagem) {
		this.qntCaracteresDepoisTruncagem = qntCaracteresDepoisTruncagem;
	}

	public Atomo getTipoSimbolo() {
		return tipoSimbolo;
	}

	public void setTipoSimbolo(Atomo tipoSimbolo) {
		this.tipoSimbolo = tipoSimbolo;
	}

	public Integer getLinha() {
		return linha;
	}

	public void setLinha(Integer linha) {
		this.linha = linha;
	}

	public List<Integer> getCincoPrimeirasLinhas() {
		return cincoPrimeirasLinhas;
	}

	public void setCincoPrimeirasLinhas(List<Integer> cincoPrimeirasLinhas) {
		this.cincoPrimeirasLinhas = cincoPrimeirasLinhas;
	}

	public void addNovaLinha(Integer novaLinha) {
		if (this.cincoPrimeirasLinhas.size() < 5) {
			this.cincoPrimeirasLinhas.add(novaLinha);
		}
	}
	
	public String toStringLex() {
		return "Lexeme: " + lexame + " , Codigo: " + codigoAtomo + " , Indice: " + numEntradaTabSimbolos;
	}
	
	public String toStringTab() {
		return "Numero_entrada_tab_simbolos: " + numEntradaTabSimbolos + " , codigo: " + codigoAtomo + 
				" , lexeme: " + lexame + " , qnt_caracteres_antes_truncagem: " + qntCaracteresAntesTruncagem + 
				" , qnt_caracteres_depois_truncagem: " + qntCaracteresDepoisTruncagem + " , cinco_primeiras_linhas_aparece: " + cincoPrimeirasLinhas;
	}

	@Override
	public String toString() {
		return "ItemTabelaDeSimbolos [numEntradaTabSimbolos=" + numEntradaTabSimbolos + ", codigoAtomo=" + codigoAtomo
				+ ", lexame=" + lexame + ", qntCaracteresAntesTruncagem=" + qntCaracteresAntesTruncagem
				+ ", qntCaracteresDepoisTruncagem=" + qntCaracteresDepoisTruncagem + ", tipoSimbolo=" + tipoSimbolo
				+ ", linha=" + linha + ", cincoPrimeirasLinhas=" + cincoPrimeirasLinhas + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cincoPrimeirasLinhas == null) ? 0 : cincoPrimeirasLinhas.hashCode());
		result = prime * result + ((codigoAtomo == null) ? 0 : codigoAtomo.hashCode());
		result = prime * result + ((lexame == null) ? 0 : lexame.hashCode());
		result = prime * result + ((linha == null) ? 0 : linha.hashCode());
		result = prime * result + ((numEntradaTabSimbolos == null) ? 0 : numEntradaTabSimbolos.hashCode());
		result = prime * result + ((qntCaracteresAntesTruncagem == null) ? 0 : qntCaracteresAntesTruncagem.hashCode());
		result = prime * result
				+ ((qntCaracteresDepoisTruncagem == null) ? 0 : qntCaracteresDepoisTruncagem.hashCode());
		result = prime * result + ((tipoSimbolo == null) ? 0 : tipoSimbolo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemTabelaDeSimbolos other = (ItemTabelaDeSimbolos) obj;
		if (!this.lexame.equalsIgnoreCase(other.lexame))
			return false;
		return true;
	}
	
	
	
	public ItemTabelaDeSimbolos(Integer numEntradaTabSimbolos, String codigoAtomo, String lexame,
			Integer qntCaracteresAntesTruncagem, Integer qntCaracteresDepoisTruncagem, Atomo tipoSimbolo, Integer linha) {
		super();
		this.numEntradaTabSimbolos = numEntradaTabSimbolos;
		this.codigoAtomo = codigoAtomo;
		this.lexame = lexame;
		this.qntCaracteresAntesTruncagem = qntCaracteresAntesTruncagem;
		this.qntCaracteresDepoisTruncagem = qntCaracteresDepoisTruncagem;
		this.tipoSimbolo = tipoSimbolo;
		this.linha = linha;
		this.cincoPrimeirasLinhas = new ArrayList<Integer>();
		this.cincoPrimeirasLinhas.add(linha);
	}
}
	