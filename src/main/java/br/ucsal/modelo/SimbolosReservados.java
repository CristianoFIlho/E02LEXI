package br.ucsal.modelo;

import br.ucsal.repositorio.Repositorio;
import br.ucsal.relatorio.RelatorioLexicos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimbolosReservados {

	private static final long serialVersionUID = 1L;

	private static final Map<String, String> SIMBOLOS_RESERVADOS = new HashMap<>(){{
		put("101 ", ";");
		put("102", "[");
		put("103", "]");
		put("104", ":");
		put("105", ",");
		put("106", "(");
		put("107", ")");
		put("108", "?");
		put("109", "{");
		put("110", "}");
		put("111", "<=");
		put("112", "<");
		put("113", ">");
		put("114", ">=");
		put("115", "==");
		put("116 ", "!=");
		put("116", "#");
		put("117", "+");
		put("118", "-");
		put("119", "*");
		put("120", "/");
		put("121", "%");
		put("122", ":=");
	}};

	public static void validar(String lexeme) {
		SIMBOLOS_RESERVADOS.forEach((key, value) -> {
			if (value.equalsIgnoreCase(lexeme)) {
				if (Repositorio.lexicos.isEmpty()) {
					Repositorio.lexicos.add(new RelatorioLexicos(1, key.trim(), lexeme, "simbolo-reservado"));
				} else {
					Integer ordem = Repositorio.lexicos.get(Repositorio.lexicos.size() - 1).getOrdem() + 1;
					Repositorio.lexicos.add(new RelatorioLexicos(ordem, key.trim(), lexeme, "simbolo-reservado"));
				}
			}
		});
	}

	public static Map<String, String> getSimbolosReservados() {
		return SIMBOLOS_RESERVADOS;
	}

	public static List<String> getValoresSimbolosReservados() {
		return SIMBOLOS_RESERVADOS.values().stream().map(String::new).collect(Collectors.toList());
	}
}
