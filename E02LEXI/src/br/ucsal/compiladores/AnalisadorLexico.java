package br.ucsal.compiladores;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalisadorLexico {

	private final BufferedReader bufferedReader;

	private final List<Atomo> atomosEncontrados = new ArrayList<>();

	private int characterAtual = 0;

	private int posicaoLinha = 1;

	private int linha = 1;

	private boolean continuarMesmoCharacter = false;

	private String cacheString = null;

	public AnalisadorLexico(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	private static final List<String> simbolos = new ArrayList<String>(Arrays.asList("!=", "!", "#", "%", "&", "/", "(",
			"*", ")", "+", ";", "]", "[", "|", "{", "}", ",", "<", "<=", "==", "=", ">", ">=", "-", " ", "\n", "\t", "\r"));

	public boolean isSimbolo(String text) {
		return simbolos.contains(text);
	}

	public boolean isIdentifier(String text) {
		// < letra > | _ | < Identifier > <letra> | < Identifier > < digito > | <
		// Identifier > _
		return text.matches("([_A-Z][_A-Z0-9]*[A-Z_])|[_]|[A-Z]");
	}

	public boolean isFunction(String text) {
		// < letra > | _ | < Identifier > <letra> | < Identifier > < digito > | <
		// Identifier > _
		return text.matches("([A-Z_][_A-Z0-9]*[A-Z0-9])|[A-Z]");
	}

	public boolean isIntegerNumber(String text) {
		// < decimal_digits >
		return text.matches("^[0-9]*$");
	}

	public boolean isDecimalDigits(String text) {
		// < digito > | < decimal_digits > < digito >
		return text.matches("^[0-9]*$|^[0-9]*$");
	}

	public boolean isFloatNumber(String text) {
		// < decimal_digits > . < decimal_digits > | < decimal_digits > . <
		// decimal_digits > < exponent_part >
		return text.matches("^[0-9]*$.^[0-9]*$|^[0-9]*$.^[0-9]*$ ^[0-9]*$");
	}

	public boolean isExponentPart(String text) {
		// e < decimal_digits > | e - < decimal_digits > | e + < decimal_digits >
		return text.matches("e ^[0-9]*$|e - ^[0-9]*$| e + ^[0-9]*$");
	}

	public boolean isConstantString(String text) {
		// "''" < Middle-String > "''" //inicia e termina com aspas duplas
		return text.matches("''^[A-Z_]*$''");
	}

	public boolean isMiddleString(String text) {
		// ( < letra > | < branco > | < digito> | $ | _ | . ) < Middle-String > | <
		// letra > | < branco > | < digito> | $ | _ | .
		return text.matches("[\"][A-Z\\d\\s$_.]+[\"]");
	}

	public boolean isCharacter(String text) {
		// "'" < letra > "'" //inicia e termina com aspas simples
		return text.matches("'[a-zA-Z]'");
	}

	public boolean isLetra(String text) {
		// a | b | c | d | e | f | g | h | i | j | k | l | m | n | o | p | q | r | s | t
		// | u | v | w | x | y | z
		return text.matches("[a-zA-Z]");
	}

	public boolean isDigito(String text) {
		// 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
		return text.matches("\\d");
	}

	public boolean isBranco(String text) {
		// “CARACTER de espaço em branco”.
		return text.matches("\\s");
	}

	public RelatorioLexico gerarRelatorio() throws IOException {
		RelatorioLexico relatorioLexico = new RelatorioLexico();
		while (this.characterAtual != -1) {
			Atomo _atomo = acharAtomo();
			if (_atomo != null) {
				this.atomosEncontrados.add(_atomo);
				
				ItemTabelaDeSimbolos novoItem = new ItemTabelaDeSimbolos(this.atomosEncontrados.size(), _atomo.getCodigo(), _atomo.getLexeme(), 
						_atomo.getTamanho(), _atomo.getTamanho(), _atomo, linha);
				relatorioLexico.getTabeladeSimbolos().addAtomo(_atomo);
				relatorioLexico.addItem(novoItem);
				
				switch (_atomo) {
				case NOM_PROGRAMA:
				case VARIAVEL:
				case NOM_FUNCAO:
				case CONS_INTEIRO:
				case CONS_REAL:
				case CONS_CADEIA:
				case CONS_CARACTER:
				
				
				
						NOM_PROGRAMA(null, "201"),
	
	
					relatorioLexico.getTabeladeSimbolos().addItem(novoItem);
					break;

				default:
					break;
				}
			}
		}
		return relatorioLexico;
	}

	private Atomo acharAtomo() throws IOException {
		String atomoAnterior = "";
		StringBuilder atomoAtual = new StringBuilder();
		Atomo atomo = null;
		boolean aspas = false;
		boolean comentarioLinha = false;
		boolean comentarioBloco = false;
		String character;

		while ((character = proxCharacter()) != null) {
			this.continuarMesmoCharacter = false;
			atomoAtual.append(character);
		

			if (!comentarioLinha && !comentarioBloco) {

				if ((isBranco(character) && !aspas) || character.equals('\n') || character.equals('\t')) {
					return atomo;
				}

				Atomo palavraReservada = Atomo.parsePalavraReservada(atomoAtual.toString().toUpperCase());

				// Caso seja comentario de linha
				if (atomoAtual.toString().equals("//")) {
					comentarioLinha = true;
					atomoAtual = new StringBuilder();
					continue;
				}

				// Caso seja comentario de bloco
				if (atomoAtual.toString().equals("/*")) {
					comentarioBloco = true;
					atomoAtual = new StringBuilder();
					continue;
				}

				// Caso seja uma palavra reservada
				if (palavraReservada != null) {
					atomoAnterior = atomoAtual.toString();
					atomo = palavraReservada;
					atomo.setLexeme(atomoAnterior);
					atomo.setTamanho(atomoAnterior.length());
					atomo.setLinha(this.linha);
					atomo.setPosLinha(this.posicaoLinha);
					continue;
				}

				// Caso seja definicao de string
				if (character.equals('"')) {

					if (atomo != null && atomo.getCodigo() != Atomo.CONS_CARACTER.getCodigo()) {
						this.continuarMesmoCharacter = true;
						return atomo;
					}

					if (aspas) {
						if (atomo != null) {
							atomo.setTamanho(atomoAtual.length());
							return atomo;
						}
						String conteudo = atomoAtual.toString();
						Atomo _atomo = Atomo.CONS_CARACTER;
						_atomo.setLexeme(conteudo);
						_atomo.setTamanho(conteudo.length());
						_atomo.setLinha(this.linha);
						_atomo.setPosLinha(this.posicaoLinha);
						return _atomo;
					}

					aspas = true;
					continue;
				}

				if (aspas) {
					if (isMiddleString(atomoAtual + "\"")) {
						atomoAnterior = atomoAtual.toString();
						continue;
					}
					if (atomo == null) {
						String conteudo = atomoAnterior + "\"";
						Atomo _atomo = Atomo.CONS_CARACTER;
						_atomo.setLexeme(conteudo);
						_atomo.setTamanho(conteudo.length());
						_atomo.setLinha(this.linha);
						_atomo.setPosLinha(this.posicaoLinha);
						atomo = _atomo;
					}
					continue;
				}

				if (isIdentifier(atomoAtual.toString())) {
					atomoAnterior = atomoAtual.toString();
					Atomo _atomo = Atomo.IDENTIFIER;
					_atomo.setLexeme(atomoAnterior);
					_atomo.setTamanho(atomoAnterior.length());
					_atomo.setLinha(this.linha);
					_atomo.setPosLinha(this.posicaoLinha);
					
					atomo = _atomo;
					continue;
				}

				if (isFunction(atomoAtual.toString())) {
					atomoAnterior = atomoAtual.toString();
					Atomo _atomo = Atomo.FUNCTION;
					_atomo.setLexeme(atomoAnterior);
					_atomo.setTamanho(atomoAnterior.length());
					_atomo.setLinha(this.linha);
					_atomo.setPosLinha(this.posicaoLinha);
					atomo = _atomo;
					continue;
				}

				if (isCharacter(atomoAtual.toString())) {
					atomoAnterior = atomoAtual.toString();
					Atomo _atomo = Atomo.CHARACTER;
					_atomo.setLexeme(atomoAnterior);
					_atomo.setTamanho(atomoAnterior.length());
					_atomo.setLinha(this.linha);
					_atomo.setPosLinha(this.posicaoLinha);
					return _atomo;
				}

				if (isIntegerNumber(atomoAtual.toString())) {
					atomoAnterior = atomoAtual.toString();
					Atomo _atomo = Atomo.INTEIRO;
					_atomo.setLexeme(atomoAnterior);
					_atomo.setTamanho(atomoAnterior.length());
					_atomo.setLinha(this.linha);
					_atomo.setPosLinha(this.posicaoLinha);
					atomo = _atomo;
					continue;
				}

				if (isFloatNumber(atomoAtual.toString() + '0')) {
					String text = proxCharacter();
					atomoAnterior = atomoAtual.toString();
					Atomo _atomo = Atomo.REAL;
					_atomo.setLexeme(atomoAnterior);
					_atomo.setTamanho(atomoAnterior.length());
					_atomo.setLinha(this.linha);
					_atomo.setPosLinha(this.posicaoLinha);
					atomo = _atomo;
					if (text != null) {
						atomoAtual.append(text);
						atomoAnterior = atomoAtual.toString();
						if (isFloatNumber(atomoAtual.toString())) {
							_atomo = Atomo.REAL;
							_atomo.setLexeme(atomoAnterior);
							_atomo.setTamanho(atomoAnterior.length());
							_atomo.setLinha(this.linha);
							_atomo.setPosLinha(this.posicaoLinha);
							atomo = _atomo;
							continue;
						}
						this.cacheString = String.valueOf((char) this.characterAtual);
					}
					continue;
				}

				if (atomo != null) {
					this.continuarMesmoCharacter = true;
					return atomo;
				}

				return null;
			}

			atomo = null;

			if (comentarioLinha) {
				if (atomoAtual.toString().equals("\n"))
					comentarioLinha = false;
				atomoAtual = new StringBuilder();
				continue;
			}

			if (atomoAtual.toString().trim().equals("*/")) {
				comentarioBloco = false;
				atomoAtual = new StringBuilder();
				continue;
			}

			if (atomoAtual.length() >= 2) {
				atomoAtual = new StringBuilder();
			}
		}
		return atomo;
	}

	private String proxCharacter() throws IOException {

		if (this.cacheString != null) {
			String text = this.cacheString;
			this.cacheString = null;
			return text;
		}

		if (this.continuarMesmoCharacter) {
			return String.valueOf(Character.toUpperCase((char) this.characterAtual));
		}

		if ((this.characterAtual = this.bufferedReader.read()) != -1) {
			
			if (this.characterAtual == 10) {
				this.linha++;
				this.posicaoLinha = 1;
				return String.valueOf('\n');
			}
			this.posicaoLinha++;
			if (isValidText(String.valueOf((char) this.characterAtual))) {
				return String.valueOf(Character.toUpperCase((char) this.characterAtual));
			}
			return null;
		}

		return null;
	}

	private boolean isValidText(String text) {
		return (isSimbolo(text) || isLetra(text) || isDigito(text) || isBranco(text));
	}
}
