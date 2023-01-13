package br.ucsal.compiladores.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ucsal.compiladores.ENUNS.Atomo;
import br.ucsal.compiladores.tabelas.ItemTabelaDeSimbolos;

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
	
		return text.matches("([_A-Z][_A-Z0-9]*[A-Z_])|[_]|[A-Z]");
	}

	public boolean isUmFuncao(String text) {
		
		return text.matches("([A-Z_][_A-Z0-9]*[A-Z0-9])|[A-Z]");
	}

	public boolean isUmNumeroInteiro(String text) {
		
		return text.matches("^[0-9]*$");
	}

	public boolean isDecimalDigits(String text) {
	
		return text.matches("^[0-9]*$|^[0-9]*$");
	}

	public boolean isNumeroReal(String text) {
		
		return text.matches("^[0-9]*$.^[0-9]*$|^[0-9]*$.^[0-9]*$ ^[0-9]*$");
	}

	public boolean isParteExpoente(String text) {
		
		return text.matches("e ^[0-9]*$|e - ^[0-9]*$| e + ^[0-9]*$");
	}

	public boolean isConstanteString(String text) {
		
		return text.matches("''^[A-Z_]*$''");
	}

	public boolean isEntreString(String text) {
	
		return text.matches("[\"][A-Z\\d\\s$_.]+[\"]");
	}

	public boolean isCharacter(String text) {
		
		return text.matches("'[a-zA-Z]'");
	}
	
	public boolean isCharacterBranco(String text) {
		
		return text.matches("\\s");
	}

	public boolean isLetra(String text) {
		
		return text.matches("[a-zA-Z]");
	}

	public boolean isDigito(String text) {
		
		return text.matches("\\d");
	}


	public OutputLexico gerarRelatorio() throws IOException {
		OutputLexico outputLexico = new OutputLexico();
		while (this.characterAtual != -1) {
			Atomo _atomo = locateAtomo();
			if (_atomo != null) {
				this.atomosEncontrados.add(_atomo);
				
				ItemTabelaDeSimbolos novoItem = new ItemTabelaDeSimbolos(this.atomosEncontrados.size(), _atomo.getCodigo(), _atomo.getLexeme(), 
						_atomo.getTamanho(), _atomo.getTamanho(), _atomo, linha);
				outputLexico.getTabeladeSimbolos().addAtomo(_atomo);
				outputLexico.addItem(novoItem);
				
				switch (_atomo) {
				case NOM_PROGRAMA:
				case VARIAVEL:
				case NOM_FUNCAO:
				case CONS_INTEIRO:
				case CONS_REAL:
				case CONS_CADEIA:
				case CONS_CARACTER:
					outputLexico.getTabeladeSimbolos().addItem(novoItem);
					break;

				default:
					break;
				}
			}
		}
		return outputLexico;
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
			if (isValidarTexto(String.valueOf((char) this.characterAtual))) {
				return String.valueOf(Character.toUpperCase((char) this.characterAtual));
			}
			return null;
		}

		return null;
	}
	private Atomo locateAtomo() throws IOException {
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

				if ((isCharacterBranco(character) && !aspas) || character.equals('\n') || character.equals('\t')) {
					return atomo;
				}

				Atomo palavraReservada = Atomo.parsePalavraReservada(atomoAtual.toString().toUpperCase());

				
				if (atomoAtual.toString().equals("//")) {
					comentarioLinha = true;
					atomoAtual = new StringBuilder();
					continue;
				}

				
				if (atomoAtual.toString().equals("/*")) {
					comentarioBloco = true;
					atomoAtual = new StringBuilder();
					continue;
				}

				
				if (palavraReservada != null) {
					atomoAnterior = atomoAtual.toString();
					atomo = palavraReservada;
					atomo.setLexeme(atomoAnterior);
					atomo.setTamanho(atomoAnterior.length());
					continue;
				}

				
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
				
						return _atomo;
					}

					aspas = true;
					continue;
				}

				if (aspas) {
					if (isEntreString(atomoAtual + "\"")) {
						atomoAnterior = atomoAtual.toString();
						continue;
					}
					if (atomo == null) {
						String conteudo = atomoAnterior + "\"";
						Atomo _atomo = Atomo.CONS_CARACTER;
						_atomo.setLexeme(conteudo);
						_atomo.setTamanho(conteudo.length());
						
						atomo = _atomo;
					}
					continue;
				}

				if (isIdentifier(atomoAtual.toString())) {
					atomoAnterior = atomoAtual.toString();
					Atomo _atomo = Atomo.CONS_CADEIA;
					_atomo.setLexeme(atomoAnterior);
					_atomo.setTamanho(atomoAnterior.length());
					
					
					atomo = _atomo;
					continue;
				}

				if (isUmFuncao(atomoAtual.toString())) {
					atomoAnterior = atomoAtual.toString();
					Atomo _atomo = Atomo.NOM_FUNCAO;
					_atomo.setLexeme(atomoAnterior);
					_atomo.setTamanho(atomoAnterior.length());
					
					atomo = _atomo;
					continue;
				}

				if (isCharacter(atomoAtual.toString())) {
					atomoAnterior = atomoAtual.toString();
					Atomo _atomo = Atomo.CONS_CADEIA;
					_atomo.setLexeme(atomoAnterior);
					_atomo.setTamanho(atomoAnterior.length());
				
					return _atomo;
				}

				if (isUmNumeroInteiro(atomoAtual.toString())) {
					atomoAnterior = atomoAtual.toString();
					Atomo _atomo = Atomo.CONS_INTEIRO;
					_atomo.setLexeme(atomoAnterior);
					_atomo.setTamanho(atomoAnterior.length());
					atomo = _atomo;
					continue;
				}

				if (isNumeroReal(atomoAtual.toString() + '0')) {
					String text = proxCharacter();
					atomoAnterior = atomoAtual.toString();
					Atomo _atomo = Atomo.CONS_REAL;
					_atomo.setLexeme(atomoAnterior);
					_atomo.setTamanho(atomoAnterior.length());
					
					atomo = _atomo;
					if (text != null) {
						atomoAtual.append(text);
						atomoAnterior = atomoAtual.toString();
						if (isNumeroReal(atomoAtual.toString())) {
							_atomo = Atomo.CONS_REAL;
							_atomo.setLexeme(atomoAnterior);
							_atomo.setTamanho(atomoAnterior.length());
							
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

	private boolean isValidarTexto(String text) {
		return (isSimbolo(text) || isLetra(text) || isDigito(text) || isCharacterBranco(text));
	}
}
