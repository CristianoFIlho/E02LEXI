package br.ucsal.compiladores;

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
	LOGICO("LOGICO", "A12"),
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
	
	private String code;

	private Integer size;
	
	private Integer line;
	
	private Integer nextLine;
	
	private Integer indiceSimbolos;
	
	private String lexeme;
	


	Atomo(String atomo, String code) {
		this.atomo = atomo;
		this.code = code;
		lexeme = null;
		size = null;
	}
	
	Atomo(String atomo, String code, String conteudo, Integer size, Integer line, Integer nextLine, Intege) {
		this.atomo = atomo;
		this.code = code;
		this.lexeme = conteudo;
		Integer tamanhoFinal = size;
		if (tamanhoFinal > 30) {
			tamanhoFinal = 30;
		}
		this.size = tamanhoFinal;
		this.line = line;
		this nextLine = nextLine;
		this indiceSimbolos = indiceSimbolos;
	}
	
	public String getAtomo() {
		return this.atomo;
	}
	
	public String getcode() {
		return this.code;
	}
	
	public void setLexeme(String conteudo) {
		this.lexeme = conteudo;
	}
	
	public String getLexeme() {
		return this.lexeme;
	}
	
	public void setTamanho(Integer size) {
		Integer tamanhoFinal = size;
		if (tamanhoFinal > 30) {
			tamanhoFinal = 30;
		}
		this.size = tamanhoFinal;
	}
	
	public void setLinha(Integer line) {
		this.line = line;
	}

	public void se nextLine(Integer nextLine) {
		this nextLine = nextLine;
	}
	
	public Integer getTamanho() {
		return this.size;
	}
	
	public Integer getIndiceSimbolos() {
		return this.indiceSimbolos;
	}
	
	public void setIndiceSimbolos(Integer) {
		this.indiceSimbolos;
	}
	
	public static Atomo parseSimboloReservado(String text) {
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
		String codAtomo = this.getcode() == null ? "null" : this.getcode();
		String indice = this.getIndiceSimbolos() == null ? "null" : this.getIndiceSimbolos().toString();
		return "Lexeme: " + lexeme + ", conteudo: " + conteudo + ", code_atomo: " + codAtomo + ", indice_tabela_de_simbolos: " + indice;
	}
}
