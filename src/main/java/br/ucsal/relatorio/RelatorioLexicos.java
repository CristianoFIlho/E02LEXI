package br.ucsal.relatorio;

public class RelatorioLexicos {
	private final Integer ordem;
	protected String codigo;
	protected String lexeme;
	protected String tipoAtomo;

	public RelatorioLexicos(Integer ordem, String codigo, String lexeme, String tipoAtomo) {
		this.ordem = ordem;
		this.codigo = codigo;
		this.lexeme = lexeme;
		this.tipoAtomo = tipoAtomo;
	}

	public String getTipoAtomo() {
		return tipoAtomo;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getLexeme() {
		return lexeme;
	}
	public Integer getOrdem() {
		return ordem;
	}

	@Override
	public String toString() {
		return "RelatorioLexicos{" +
				"ordem=" + ordem +
				", codigo='" + codigo + '\'' +
				", lexeme='" + lexeme + '\'' +
				", tipoAtomo='" + tipoAtomo + '\'' +
				'}';
	}
}
