package br.ucsal.modelo;

import br.ucsal.repositorio.Repositorio;
import br.ucsal.relatorio.RelatorioLexicos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PalavrasReservadas {
	private static final long serialVersionUID = 1L;

	private static final Map<String, String> PALAVRAS_RESERVADAS = new HashMap<>(){

	{
		put("001", "PROGRAMA");
		put("002", "DECLARACOES");
		put("003", "FIM-DECLARACOES");
		put("004", "FUNCOES");
		put("005", "FIM-FUNCOES");
		put("006", "FIM-PROGRAMA");
		put("007", "TIPO-VAR");
		put("008", "VAZIO");
		put("009", "REAL");
		put("010", "INTEIRO");
		put("011", "CADEIA");
		put("012", "LOGICO");
		put("013", "CARACTER");
		put("014", "TIPO-FUNC");
		put("015", "FIM-FUNC");
		put("016", "TIPO-PARAM");
		put("017", "SE");
		put("018", "FIM-SE");
		put("019", "SENAO");
		put("020", "ENQUANTO");
		put("021", "FIM-ENQUANTO");
		put("022", "RETORNA");
		put("023", "PAUSA");
		put("024", "IMPRIME");
		put("025", "TRUE");
		put("026", "FALSE");
		
	}};

	public static void validar(String lexeme) {
		PALAVRAS_RESERVADAS.forEach((key, value) -> {
			if (value.equalsIgnoreCase(lexeme)) {
				if (Repositorio.lexicos.isEmpty()) {
					Repositorio.lexicos.add(new RelatorioLexicos(1, key.trim(), lexeme, "palavra-reservada"));
				} else {
					Integer ordem = Repositorio.lexicos.get(Repositorio.lexicos.size() - 1).getOrdem() + 1;
					Repositorio.lexicos.add(new RelatorioLexicos(ordem, key.trim(), lexeme, "palavra-reservada"));
				}
			}
		});
	}

	public static Map<String, String> getPalavrasReservadas() {
		return PALAVRAS_RESERVADAS;
	}

	public static List<String> getValoresPalavrasReservadas() {
		return PALAVRAS_RESERVADAS.values().stream().map(String::new).collect(Collectors.toList());
	}
}
