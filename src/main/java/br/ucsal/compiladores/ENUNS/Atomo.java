package br.ucsal.compiladores.ENUNS;

public enum Atomo {
	PROGRAMA("PROGRAMA", "001"),
	DECLARACOES("DECLARACOES", "002"),
	FIM_DECLARACOES("FIM-DECLARACOES", "003"),
	FUNCOES("FUNCOES", "004"),
	FIM_FUNCOES("FIM-FUNCOES", "005"),
	FIM_PROGRAMA("FIM-PROGRAMA", "006"),
	TIPO_VAR("TIPO-VAR", "007"),
	VAZIO("VAZIO", "008"),
	REAL("REAL", "009"),
	INTEIRO("INTEIRO", "010"),
	CADEIA("CADEIA", "011"),
	LOGICO("LOGICO", "012"),
	CARACTER("CARACTER", "013"),
	TIPO_FUNC("TIPO-FUNC", "014"),
	FIM_FUNC("FIM-FUNC", "015"),
	TIPO_PARAM("TIPO-PARAM", "016"),
	SE("SE", "017"),
	FIM_SE("FIM_SE", "018"),
	SENAO("SENAO", "019"),
	ENQUANTO("ENQUANTO", "020"),
	FIM_ENQUANTO("FIM_ENQUANTO", "021"),
	RETORNA("RETORNA", "022"),
	PAUSA("PAUSA", "023"),
	IMPRIME("IMPRIME", "024"),
	TRUE("TRUE", "025"),
	FALSO("FALSO", "026"),
	PONTO_VIRGULA(";", "101"),
	ABRE_COLCHETE("[", "102"),
	FECHA_COLCHETE("]", "103"),
	DOIS_PONTOS(":", "104"),
	VIRGULA(",", "105"),
	ABRE_PARENTESES("(", "106"),
	FECHA_PARENTESES(")", "1O7"),
	INTERROGACAO("INTERROGACAO", "108"),
	ABRE_CHAVE("{", "109"),
	FECHA_CHAVE("}", "110"),
	MENOR_IGUAL("<=", "111"),
	MAIOR(">", "112"),
	MENOR("<", "113"),
	MAIOR_IGUAL(">=", "114"),
	IGUAL_IGUAL("==", "115"),
	DIFERENTE("!=", "116"),
	DIFERENTE2("#", "117"),
	MAIS("+", "118"),
	MENOS("-", "119"),
	MULTIPLICA("*", "120"),
	BARRA("/", "121"),
	PORCENTAGEM("%", "122"),
	DE(":=", "123"),
	NOM_PROGRAMA(null, "201"),
	VARIAVEL(null, "202"),
	NOM_FUNCAO(null, "203"),
	CONS_INTEIRO(null, "204"),
	CONS_REAL(null, "205"),
	CONS_CADEIA(null, "206"),
	CONS_CARACTER(null, "207");

	private String atomo;
	
	private Integer tamanhoDoAtomo;
	
	private String codigo;
	
	private String lexeme;
	
	private Integer indiceTabSimbolos;

	Atomo(String atomo, String codigo) {
		this.atomo = atomo;
		this.codigo = codigo;
		lexeme = null;
		tamanhoDoAtomo = null;
	}
	
	Atomo(String atomo, String codigo, String conteudo, Integer tamanho, Integer linha, Integer posLinha, Integer indiceTabSimbolos) {
		this.atomo = atomo;
		this.codigo = codigo;
		this.lexeme = conteudo;
		Integer tamanhoFinal = tamanho;
		if (tamanhoFinal > 30) {
			tamanhoFinal = 30;
		}
		this.tamanhoDoAtomo = tamanhoFinal;
		
		this.indiceTabSimbolos = indiceTabSimbolos;
	}
	
	public String getAtomo() {
		return this.atomo;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public void setLexeme(String conteudo) {
		this.lexeme = conteudo;
	}
	
	public String getLexeme() {
		return this.lexeme;
	}
	
	public void setTamanho(Integer tamanho) {
		Integer tamanhoFinal = tamanho;
		if (tamanhoFinal > 30) {
			tamanhoFinal = 30;
		}
		this.tamanhoDoAtomo = tamanhoFinal;
	}
	

	
	public Integer getTamanho() {
		return this.tamanhoDoAtomo;
	}
	
	public Integer getIndiceTabSimbolos() {
		return this.indiceTabSimbolos;
	}
	
	public void setIndiceTabSimbolos(Integer indiceTabSimbolos) {
		this.indiceTabSimbolos = indiceTabSimbolos;
	}
	
	public static Atomo parsePalavraReservada(String text) {
		for (Atomo palavraReservada : values()) {
		      if (palavraReservada.getAtomo() != null && 
		        palavraReservada.getAtomo().equalsIgnoreCase(text))
		        return palavraReservada; 
		    } 
		    return null;
	}
	
	@Override
	public String toString() {
		String lexeme = this.getAtomo() == null ? "null" : this.getAtomo();
		String conteudo = this.getLexeme() == null ? "null" : this.getLexeme();
		String codAtomo = this.getCodigo() == null ? "null" : this.getCodigo();
		String indice = this.getIndiceTabSimbolos() == null ? "null" : this.getIndiceTabSimbolos().toString();
		return "Lexeme: " + lexeme + ", conteudo: " + conteudo + ", codigo: " + codAtomo + ", tabela de simbolos: " + indice;
	}
}
