package br.ucsal.relatorio;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GerarRelatorio {

    public static void gerarRelatorioLexico(List<RelatorioLexicos> relatorio, String caminhoArquivo) throws IOException {
 
    	
        try (FileWriter arquivo = new FileWriter(caminhoArquivo.replace(".222", "") + "." + "LEX")) {
            try (PrintWriter relatorioWriter = new PrintWriter(arquivo)) {
                relatorioWriter.println(criarCabecalho());
                relatorio.forEach(relatorioWriter::println);
            }
        }
    }

    public static void gerarRelatorioSimbolos(List<RelatorioSimbolos> relatorio, String caminhoArquivo) throws IOException {

        try (FileWriter arquivo = new FileWriter(caminhoArquivo.replace(".222", "") + "." + "TAB")) {
            try (PrintWriter relatorioWriter = new PrintWriter(arquivo)) {
                relatorioWriter.println(criarCabecalho());
                relatorio.forEach(relatorioWriter::println);
            }
        }
    }


    public static String criarCabecalho() {
        return new StringBuilder().append("Equipe 01\n")
                .append("Componentes:\n")
                .append("Bruna Ribeiro, bruna.ribeiro@ucsal.edu.br, (71) 99247-5015\n")
                .append("Jamile Conceição, jamile.conceicao@ucsal.edu.br, (71) 98133-0263\n")
                .append("Lucas Walter, lucas.walter@ucsal.edu.br, (71) 99163-2197\n")
                .append("Murilo Caetano, murilo.caetano@ucsal.edu.br, (71) 98173-9182\n").toString();
    }
}
