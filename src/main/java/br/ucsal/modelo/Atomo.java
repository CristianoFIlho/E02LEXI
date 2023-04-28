package br.ucsal.modelo;

public class Atomo {
    private final String tipo;
    private final String codigo;
    private final String atomo;

    public Atomo(String tipo, String codigo, String atomo) {
        this.tipo = tipo;
        this.codigo = codigo;
        this.atomo = atomo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getAtomo() {
        return atomo;
    }

}
