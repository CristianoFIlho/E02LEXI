package br.ucsal.compiladores;

public enum Atomo {
	PROGRAMA("PROGRAMA", "001"),
	DECLARACOES("DECLARACOES", "002"),
	FIM_DECLARACOES("FIM-DECLARACOES", "003"),
	FUNCOES("FUNCOES", "004"),
	FIM_FUNCOES("FIM-FUNCOES", "005"),
	FIM_PROGRAMA("FIM-PROGRAMA", "006"),
	TIPO-VAR("TIPO-VAR", "007"),
	VAZIO("VAZIO", "008"),
	REAL("REAL", "009"),
	INTEIRO("INTEIRO", "010"),
	CADEIA("CADEIA", "011"),
	LOGICO("LOGICO", "A12"),
	CARACTER("CARACTER", "013"),
	TIPO_FUNC("TIPO-FUNC", "014"),
	FIM_FUNC("FIM-FUNC", "015"),
	TIPO_PARAM("TIPO-PARAM", "016"),
	DIFERENTE("!=", "B01"),
	DIFERENTE2("#", "B01"),
	NAO("!", "BO2"),
	E("&", "BO3"),
	RESTO("%", "BO4"),
	ABRE_PARENTESES("(", "BO5"),
	BARRA("/", "BO6"),
	FECHA_PARENTESES(")", "BO7"),
	MULTIPLICA("*", "BO8"),
	PONTO_VIRGULA(";", "BO9"),
	MAIS("+", "B10"),
	ABRE_COLCHETE("[", "B11"),
	FECHA_COLCHETE("]", "B12"),
	ABRE_CHAVE("{", "B13"),
	OU("|", "B14"),
	VIRGULA(",", "B15"),
	FECHA_CHAVE("}", "B16"),
	MENOR_IGUAL("<=", "B17"),
	MENOR("<", "B18"),
	IGUAL("=", "B19"),
	IGUAL_IGUAL("==", "B20"),
	MAIOR_IGUAL(">=", "B21"),
	MAIOR(">", "B22"),
	MENOS("-", "B23"),
	IDENTIFIER(null, "C01"),
	CONSTANT_STRING(null, "C02"),
	INTEGER_NUMBER(null, "C03"),
	FUNCTION(null, "C04"),
	CHARACTER(null, "C05"),
	FLOAT_NUMBER(null, "C06");

	private String atomo;
	
	private String codigo;
	
	private String lexeme;
	
	private Integer tamanho;
	
	private Integer linha;
	
	private Integer posLinha;
	
	private Integer indiceTabSimbolos;

	Atomo(String atomo, String codigo) {
		this.atomo = atomo;
		this.codigo = codigo;
		lexeme = null;
		tamanho = null;
	}
	
	Atomo(String atomo, String codigo, String conteudo, Integer tamanho, Integer linha, Integer posLinha, Integer indiceTabSimbolos) {
		this.atomo = atomo;
		this.codigo = codigo;
		this.lexeme = conteudo;
		Integer tamanhoFinal = tamanho;
		if (tamanhoFinal > 30) {
			tamanhoFinal = 30;
		}
		this.tamanho = tamanhoFinal;
		this.linha = linha;
		this.posLinha = posLinha;
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
		this.tamanho = tamanhoFinal;
	}
	
	public void setLinha(Integer linha) {
		this.linha = linha;
	}

	public void setPosLinha(Integer posLinha) {
		this.posLinha = posLinha;
	}
	
	public Integer getTamanho() {
		return this.tamanho;
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
		return "Lexeme: " + lexeme + ", conteudo: " + conteudo + ", codigo_atomo: " + codAtomo + ", indice_tabela_de_simbolos: " + indice;
	}
}
