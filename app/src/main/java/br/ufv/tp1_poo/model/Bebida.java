package br.ufv.tp1_poo.model;

public class Bebida extends Produto {
    public Bebida(int preco, String nome, int quantidade, String descricao, String imagem,
                  String categoria, String observacao, String tamanho) {
        super(preco, nome, quantidade, descricao, imagem, categoria, observacao, tamanho);
    }

    @Override
    public String getTamanhoFormatado() {
        if (getTamanho().equals("300 ml") || getTamanho().equals("600 ml") || getTamanho().equals("1L")) {
            return getTamanho();
        } else {
            return "Tamanho inválido";
        }
    }
}
